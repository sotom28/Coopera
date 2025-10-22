package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Integer> {
}
