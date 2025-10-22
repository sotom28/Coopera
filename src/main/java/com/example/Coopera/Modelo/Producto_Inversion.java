package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTO_INVERSION")
@Data
@NoArgsConstructor
public class Producto_Inversion implements Serializable {

	@Id
	@Column(name = "cod_prod_inv", nullable = false)
	private Integer codProdInv;

	@Column(name = "nombre_prod_inv", nullable = false, length = 50)
	private String nombreProdInv;

	@Column(name = "meses_minimo_ahorro")
	private Integer mesesMinimoAhorro;

	@Column(name = "porc_maximo_rescate")
	private Integer porcMaximoRescate;
}
