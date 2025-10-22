package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMUNA")
@IdClass(ComunaId.class)
@Data
@NoArgsConstructor
public class Comuna implements Serializable {
	@Id
	@Column(name = "cod_region", nullable = false)
	private Integer codRegion;

	@Id
	@Column(name = "cod_provincia", nullable = false)
	private Integer codProvincia;

	@Id
	@Column(name = "cod_comuna", nullable = false)
	private Integer codComuna;

	@Column(name = "nombre_comuna", nullable = false, length = 50)
	private String nombreComuna;
}

@Data
@NoArgsConstructor
class ComunaId implements java.io.Serializable {
	private Integer codRegion;
	private Integer codProvincia;
	private Integer codComuna;
}
