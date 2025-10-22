package com.example.Coopera.Modelo;

import java.io.Serializable;








@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "MULTA_MORA")
public class Multa_Mora implements Serializable {
    private static final long serialVersionUID = 1L;

    @jakarta.persistence.EmbeddedId
    private MultaMoraId id;

    @jakarta.persistence.Column(name = "porc_multa", nullable = false)
    private Integer porcMulta;


}








