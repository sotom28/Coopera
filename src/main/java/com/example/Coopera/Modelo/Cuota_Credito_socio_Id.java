package com.example.Coopera.Modelo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cuota_Credito_socio_Id implements Serializable {
    private Long nroSolicCredito;
    private Integer nroCuota;
}
