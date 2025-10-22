package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTO_INVERSION_SOCIO")
@Data
@NoArgsConstructor
public class Producto_Inversion_socio implements Serializable {
	@Id
	@Column(name = "nro_solic_prod", nullable = false)
	private Long nroSolicProd;

	@Column(name = "nro_socio", nullable = false)
	private Integer nroSocio;

	@Column(name = "fecha_solic_prod", nullable = false)
	private java.sql.Date fechaSolicProd;

	@Column(name = "ahorro_minimo_mensual", nullable = false)
	private Integer ahorroMinimoMensual;

	@Column(name = "dia_pago_mensual", nullable = false)
	private Integer diaPagoMensual;

	@Column(name = "monto_total_ahorrado", nullable = false)
	private Integer montoTotalAhorrado;

	@Column(name = "cod_prod_inv", nullable = false)
	private Integer codProdInv;
}
