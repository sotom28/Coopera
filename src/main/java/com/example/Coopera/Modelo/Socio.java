package com.example.Coopera.Modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Table(name = "SOCIO")
@Data
public class Socio {

    @Id
    @Column(name = "NRO_SOCIO")
    private Long numeroSocio;

    @Column(name = "NUMRUN")
    private Integer numrun;

    @Column(name = "DVRUN")
    private String dvrun;

    @Column(name = "PNOMBRE")
    private String Pnombre;

    @Column(name = "SNOMBRE")
    private String Snombre;

    @Column(name = "APPATERNO")
    private String appaterno;

    @Column(name = "APMATERNO")
    private String apmaterno;

    @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;

    @Column(name = "FECHA_INSCRIPCION")
    private Date fechaIngreso;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "FONO_CONTACTO")
    private Integer fono_contacto;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "COD_REGION")
    private String codregion;

    @Column(name = "COD_PROVINCIA")
    private String codprovincia;

    @Column(name = "COD_COMUNA")
    private String codcomuna;

    @Column(name = "COD_PROF_OFIC")
    private String prof_ofic;

    @Column(name = "COD_TIPO_SOCIO")
    private Integer tipo_socio;
}
