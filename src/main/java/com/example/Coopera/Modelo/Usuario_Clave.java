package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIO_CLAVE")
@Data
@NoArgsConstructor
public class Usuario_Clave implements Serializable {
	@Id
	@Column(name = "nro_socio", nullable = false)
	private Integer nroSocio;

	@Column(name = "numrun_socio", nullable = false, length = 15)
	private String numrunSocio;

	@Column(name = "nombre_socio", nullable = false, length = 60)
	private String nombreSocio;

	@Column(name = "nombre_usuario", nullable = false, length = 20)
	private String nombreUsuario;

	@Column(name = "clave_usuario", nullable = false, length = 20)
	private String claveUsuario;
}
