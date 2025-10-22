package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIMIENTO")
@Data
@NoArgsConstructor
public class Movimiento implements Serializable {
	@Id
	@Column(name = "nro_movimiento", nullable = false)
	private Long nroMovimiento;

	@Column(name = "nro_solic_prod", nullable = false)
	private Long nroSolicProd;

	@Column(name = "nro_socio", nullable = false)
	private Integer nroSocio;

	@Column(name = "cod_prod_inv", nullable = false)
	private Integer codProdInv;

	@Column(name = "fecha_movimiento", nullable = false)
	private java.sql.Date fechaMovimiento;

	@Column(name = "monto_movimiento", nullable = false)
	private Integer montoMovimiento;

	@Column(name = "cod_tipo_mov", nullable = false)
	private Integer codTipoMov;
}
