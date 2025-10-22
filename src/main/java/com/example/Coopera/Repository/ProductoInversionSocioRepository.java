package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Producto_Inversion_socio;

@Repository
public interface ProductoInversionSocioRepository extends JpaRepository<Producto_Inversion_socio, Long> {
}
