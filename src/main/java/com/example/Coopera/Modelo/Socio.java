package com.example.Coopera.Modelo;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import lombok.Data;

@Entity
@Table(name = "SOCIO")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOCIO_GEN")
    @SequenceGenerator(name = "SEQ_SOCIO_GEN", sequenceName = "SEQ_SOCIO", allocationSize = 1)
    @Column(name = "NRO_SOCIO") 
    private Long numeroSocio;

    @Column(name = "NUMRUN")
    private Integer numrun;

    @Column(name = "DVRUN")
    private String dvrun;

    @Column(name = "PNOMBRE")
    @JsonProperty("pnombre")
    private String Pnombre;

    @Column(name = "SNOMBRE")
    @JsonProperty("snombre")
    private String Snombre;

    @Column(name = "APPATERNO")
    private String appaterno;

    @Column(name = "APMATERNO")
    private String apmaterno;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(name = "FOTO")
    private byte[] foto;

    @Column(name = "FECHA_NACIMIENTO")
    private String fechaNacimiento;

    @Column(name = "FECHA_INSCRIPCION")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "FONO_CONTACTO")
    @JsonProperty("fono_contacto")
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
