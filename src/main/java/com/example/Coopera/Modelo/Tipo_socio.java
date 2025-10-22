package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TIPO_SOCIO")
@Data
@NoArgsConstructor
public class Tipo_socio implements Serializable {

	@Id
	@Column(name = "cod_tipo_socio", nullable = false)
	private Integer codTipoSocio;

	@Column(name = "nombre_tipo_socio", nullable = false, length = 30)
	private String nombreTipoSocio;

	@Column(name = "promedio_renta", nullable = false, length = 40)
	private String promedioRenta;
}
