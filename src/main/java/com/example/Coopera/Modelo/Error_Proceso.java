package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ERROR_PROCESO")
@Data
@NoArgsConstructor
public class Error_Proceso implements Serializable {
	@Id
	@Column(name = "correl_error", nullable = false)
	private Integer correlError;

	@Column(name = "sentencia_error", nullable = false, length = 120)
	private String sentenciaError;

	@Column(name = "descrip_error", nullable = false, length = 255)
	private String descripError;
}
