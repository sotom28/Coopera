package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
