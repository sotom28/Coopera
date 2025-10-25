package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Pago_Mensual_Credito;
import com.example.Coopera.Modelo.Pago_Mensual_Credito_Id;

@Repository
public interface PagoMensualCreditoRepository extends JpaRepository<Pago_Mensual_Credito, Pago_Mensual_Credito_Id> {
}
