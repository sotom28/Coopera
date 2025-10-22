package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRAMO_3RA_EDAD")
@Data
@NoArgsConstructor
public class Tramo_3ra_Edad implements Serializable {
	@Id
	@Column(name = "rango_edad_min", nullable = false)
	private Integer rangoEdadMin;

	@Column(name = "rango_edad_max", nullable = false)
	private Integer rangoEdadMax;

	@Column(name = "factor", nullable = false)
	private Integer factor;
}
