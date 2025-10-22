package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGION")
@Data
@NoArgsConstructor
public class Region implements Serializable {

	@Id
	@Column(name = "cod_region", nullable = false)
	private Integer codRegion;

	@Column(name = "nombre_region", nullable = false, length = 50)
	private String nombreRegion;
}
