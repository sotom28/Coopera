package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "PAGO_MENSUAL_CREDITO")
public class Pago_Mensual_Credito implements Serializable {
	@Id
	@Column(name = "fecha_proceso", nullable = false, length = 7)
	private String fechaProceso;

	@Column(name = "nro_socio", nullable = false)
	private Integer nroSocio;

	@Column(name = "run_socio", nullable = false, length = 12)
	private String runSocio;

	@Column(name = "nro_solic_credito", nullable = false)
	private Long nroSolicCredito;

	@Column(name = "tipo_credito", nullable = false, length = 40)
	private String tipoCredito;

	@Column(name = "monto_total_credito", nullable = false)
	private Long montoTotalCredito;

	@Column(name = "nro_total_cuotas", nullable = false)
	private Integer nroTotalCuotas;

	@Column(name = "nro_cuota_mes", nullable = false)
	private Integer nroCuotaMes;

	@Column(name = "valor_cuota_mes", nullable = false)
	private Long valorCuotaMes;

	@Column(name = "fecha_venc_cuota_mes", nullable = false)
	private java.sql.Date fechaVencCuotaMes;

	@Column(name = "saldo_pago_mes_ant", nullable = false)
	private Long saldoPagoMesAnt;

	@Column(name = "dias_atraso_pago_mes_ant", nullable = false)
	private Integer diasAtrasoPagoMesAnt;

	@Column(name = "multa_atraso_pago_mes_ant", nullable = false)
	private Long multaAtrasoPagoMesAnt;

	@Column(name = "valor_rebajar_65_annos", nullable = false)
	private Integer valorRebajar65Annos;

	@Column(name = "valor_total_cuota_mes", nullable = false)
	private Long valorTotalCuotaMes;
}

