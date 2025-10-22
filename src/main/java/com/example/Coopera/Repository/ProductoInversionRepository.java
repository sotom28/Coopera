package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Producto_Inversion;

@Repository
public interface ProductoInversionRepository extends JpaRepository<Producto_Inversion, Integer> {
}
