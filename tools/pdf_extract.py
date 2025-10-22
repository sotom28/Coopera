#!/usr/bin/env python3
"""
Extract tables from a PDF and map columns to the Socio JPA model.

Outputs (tools/output/):
- extracted_table_page{n}_table{m}.csv
- socio_mapped.csv (if mapping found)
- socio_inserts.sql (INSERT statements)
- extracted_tables.xlsx

Usage: python tools\pdf_extract.py
"""
import os
import sys
import csv
import json
import unicodedata
from pathlib import Path

try:
    import pdfplumber
    import pandas as pd
except Exception as e:
    print("Missing dependencies. Run: python -m pip install pdfplumber pandas openpyxl")
    raise


PDF_PATH = Path(r"c:\Users\rodol\Documents\Coopera\ANEXO A.pdf")
OUT_DIR = Path(__file__).parent / "output"
OUT_DIR.mkdir(parents=True, exist_ok=True)

# Target Socio columns (DB names)
TARGET_COLS = [
    'NRO_SOCIO','NUMRUN','DVRUN','PNOMBRE','SNOMBRE','APPATERNO','APMATERNO',
    'FOTO','FECHA_NACIMIENTO','FECHA_INSCRIPCION','CORREO','FONO_CONTACTO','DIRECCION',
    'COD_REGION','COD_PROVINCIA','COD_COMUNA','COD_PROF_OFIC','COD_TIPO_SOCIO'
]


def normalize(text: str) -> str:
    if text is None:
        return ''
    text = str(text)
    text = text.strip()
    text = unicodedata.normalize('NFKD', text)
    text = ''.join([c for c in text if not unicodedata.combining(c)])
    return text.upper()


def header_guess_map(headers):
    """Try to map arbitrary headers to TARGET_COLS using keyword matching."""
    mapping = {}
    # keywords per target (lowercase, accentless)
    keywords = {
        'NRO_SOCIO': ['nro','numero','nro_socio','nro socio','num_socio','id'],
        'NUMRUN': ['run','numrun','rut'],
        'DVRUN': ['dv','dvrun','dv_run','digito','dv'],
        'PNOMBRE': ['primer nombre','pnombre','p_nombre','nombre1','nombres'],
        'SNOMBRE': ['segundo nombre','snombre','s_nombre','nombre2'],
        'APPATERNO': ['apellido paterno','appaterno','apellido1','apellidopaterno'],
        'APMATERNO': ['apellido materno','apmaterno','apellido2','apellidomaterno'],
        'FECHA_NACIMIENTO': ['fecha nacimiento','nacimiento','fec_nac','fnac'],
        'FECHA_INSCRIPCION': ['fecha inscripcion','inscripcion','fecha_ingreso','fec_insc'],
        'CORREO': ['correo','email','e-mail'],
        'FONO_CONTACTO': ['fono','telefono','telefono contacto','telefono_contacto','fono_contacto'],
        'DIRECCION': ['direccion','domicilio'],
        'COD_REGION': ['region','cod_region'],
        'COD_PROVINCIA': ['provincia','cod_provincia'],
        'COD_COMUNA': ['comuna','cod_comuna'],
        'COD_PROF_OFIC': ['prof','prof_ofic','profesion','oficio'],
        'COD_TIPO_SOCIO': ['tipo socio','tipo','tipo_socio']
    }

    norm_keywords = {k: [normalize(w) for w in v] for k, v in keywords.items()}

    for i, h in enumerate(headers):
        nh = normalize(h)
        found = None
        for target, kws in norm_keywords.items():
            for kw in kws:
                if kw in nh or nh in kw:
                    found = target
                    break
            if found:
                break
        if found:
            mapping[i] = found
        else:
            mapping[i] = None
    return mapping


def to_sql_value(val):
    if pd.isna(val):
        return 'NULL'
    if isinstance(val, (int, float)):
        return str(int(val)) if float(val).is_integer() else str(val)
    s = str(val).replace("'", "''")
    return "'{}'".format(s)


def main():
    if not PDF_PATH.exists():
        print(f"PDF not found at {PDF_PATH}. Please check path.")
        sys.exit(1)

    xls_writer = pd.ExcelWriter(OUT_DIR / 'extracted_tables.xlsx', engine='openpyxl')
    all_mapped_rows = []
    inserts = []

    with pdfplumber.open(str(PDF_PATH)) as pdf:
        for pindex, page in enumerate(pdf.pages, start=1):
            tables = page.extract_tables()
            if not tables:
                continue
            for tindex, table in enumerate(tables, start=1):
                # table is list of rows (list of lists)
                # assume first row headers
                if len(table) < 2:
                    continue
                headers = table[0]
                rows = table[1:]
                # write raw CSV
                csv_path = OUT_DIR / f'extracted_table_page{pindex}_table{tindex}.csv'
                with open(csv_path, 'w', newline='', encoding='utf-8') as f:
                    writer = csv.writer(f)
                    writer.writerows(table)
                print(f'Wrote {csv_path}')

                # pandas df
                df = pd.DataFrame(rows, columns=headers)
                try:
                    df = df.applymap(lambda x: x.strip() if isinstance(x, str) else x)
                except Exception:
                    pass
                sheet_name = f'P{pindex}_T{tindex}'[:31]
                df.to_excel(xls_writer, sheet_name=sheet_name, index=False)

                # attempt mapping
                mapping = header_guess_map(headers)
                # build mapped rows if mapping covers at least one important field
                mapped = []
                for r in rows:
                    out = {col: None for col in TARGET_COLS}
                    for i, cell in enumerate(r):
                        target = mapping.get(i)
                        if target:
                            out[target] = cell
                    mapped.append(out)

                # check if mapping found some keys
                # if majority of rows have NRO_SOCIO or PNOMBRE set, accept mapping
                valid_count = sum(1 for m in mapped if m.get('NRO_SOCIO') or m.get('PNOMBRE'))
                if valid_count >= 1:
                    mapped_df = pd.DataFrame(mapped)
                    mapped_csv = OUT_DIR / 'socio_mapped.csv'
                    # append or create
                    if mapped_csv.exists():
                        mapped_df.to_csv(mapped_csv, mode='a', header=False, index=False, encoding='utf-8')
                    else:
                        mapped_df.to_csv(mapped_csv, index=False, encoding='utf-8')

                    # generate SQL inserts
                    for _, row in mapped_df.iterrows():
                        cols = []
                        vals = []
                        for col in TARGET_COLS:
                            v = row.get(col)
                            if v is None or (isinstance(v, float) and pd.isna(v)) or (isinstance(v, str) and v.strip()==''):
                                continue
                            cols.append(col)
                            vals.append(to_sql_value(v))
                        if cols:
                            sql = f"INSERT INTO SOCIO ({', '.join(cols)}) VALUES ({', '.join(vals)});"
                            inserts.append(sql)
                    all_mapped_rows.extend(mapped)

    xls_writer.save()
    if inserts:
        with open(OUT_DIR / 'socio_inserts.sql', 'w', encoding='utf-8') as f:
            f.write('\n'.join(inserts))
        print(f'Wrote {OUT_DIR / "socio_inserts.sql"}')

    if (OUT_DIR / 'socio_mapped.csv').exists():
        print('Mapped CSV generated at', OUT_DIR / 'socio_mapped.csv')
    else:
        print('No mapped tables found; check the raw extracted CSV files in', OUT_DIR)


if __name__ == '__main__':
    main()
