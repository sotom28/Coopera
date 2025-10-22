package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CREDITO")
@Data
@NoArgsConstructor
public class Credito implements Serializable {
	@Id
	@Column(name = "cod_credito", nullable = false)
	private Integer codCredito;

	@Column(name = "nombre_credito", nullable = false, length = 50)
	private String nombreCredito;

	@Column(name = "desc_credito", nullable = false, length = 400)
	private String descCredito;

	@Column(name = "tasa_interes_anual")
	private Double tasaInteresAnual;

	@Column(name = "nro_maximo_cuotas")
	private Integer nroMaximoCuotas;
}
