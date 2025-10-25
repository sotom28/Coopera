package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Coopera.Modelo.Cuota_Credito_socio;
import com.example.Coopera.Modelo.Cuota_Credito_socio_Id;

@Repository
public interface CuotaCreditoSocioRepository extends JpaRepository<Cuota_Credito_socio, Cuota_Credito_socio_Id> {
}
