package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CREDITO_SOCIO")
@Data
@NoArgsConstructor
public class Credito_Socio implements Serializable {
	@Id
	@Column(name = "nro_solic_credito", nullable = false)
	private Long nroSolicCredito;

	@Column(name = "nro_socio", nullable = false)
	private Integer nroSocio;

	@Column(name = "fecha_solic_cred", nullable = false)
	private java.sql.Date fechaSolicCred;

	@Column(name = "fecha_otorga_cred", nullable = false)
	private java.sql.Date fechaOtorgaCred;

	@Column(name = "monto_solicitado", nullable = false)
	private Integer montoSolicitado;

	@Column(name = "monto_total_credito", nullable = false)
	private Integer montoTotalCredito;

	@Column(name = "total_cuotas_credito", nullable = false)
	private Integer totalCuotasCredito;

	@Column(name = "cod_credito", nullable = false)
	private Integer codCredito;
}
