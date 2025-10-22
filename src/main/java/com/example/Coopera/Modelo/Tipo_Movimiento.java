package com.example.Coopera.Modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "TIPO_MOVIMIENTO")
@Data

public class Tipo_Movimiento implements Serializable {
	@Id
	@Column(name = "cod_tipo_mov", nullable = false)
	private Integer codTipoMov;

	@Column(name = "nombre_tipo_mov", nullable = false, length = 50)
	private String nombreTipoMov;
    
}
