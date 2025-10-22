package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUOTA_CREDITO_SOCIO")
@Data
@NoArgsConstructor
public class Cuota_Credito_socio implements Serializable {
	@Id
	@Column(name = "nro_solic_credito", nullable = false)
	private Long nroSolicCredito;

	@Id
	@Column(name = "nro_cuota", nullable = false)
	private Integer nroCuota;

	@Column(name = "fecha_venc_cuota", nullable = false)
	private java.sql.Date fechaVencCuota;

	@Column(name = "valor_cuota", nullable = false)
	private Integer valorCuota;

	@Column(name = "fecha_pago_cuota")
	private java.sql.Date fechaPagoCuota;

	@Column(name = "monto_pagado")
	private Integer montoPagado;

	@Column(name = "saldo_por_pagar")
	private Integer saldoPorPagar;

	@Column(name = "cod_forma_pago")
	private Integer codFormaPago;
}
