package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FORMA_PAGO")
@Data
@NoArgsConstructor
public class Forma_Pago implements Serializable {
	@Id
	@Column(name = "cod_forma_pago", nullable = false)
	private Integer codFormaPago;

	@Column(name = "nombre_forma_pago", nullable = false, length = 50)
	private String nombreFormaPago;
}
