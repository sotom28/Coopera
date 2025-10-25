package com.example.Coopera.Modelo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pago_Mensual_Credito_Id implements Serializable {
    private String fechaProceso;
    private Integer nroSocio;
    private Long nroSolicCredito;
    private Integer nroCuotaMes;
}
