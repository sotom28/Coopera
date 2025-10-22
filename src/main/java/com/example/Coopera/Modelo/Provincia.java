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
@Table(name = "PROVINCIA")
@IdClass(ProvinciaId.class)
@Data
@NoArgsConstructor
public class Provincia implements Serializable {
	@Id
	@Column(name = "cod_region", nullable = false)
	private Integer codRegion;

	@Id
	@Column(name = "cod_provincia", nullable = false)
	private Integer codProvincia;

	@Column(name = "nombre_provincia", nullable = false, length = 50)
	private String nombreProvincia;
}

@Data
@NoArgsConstructor
class ProvinciaId implements java.io.Serializable {
	private Integer codRegion;
	private Integer codProvincia;
}
