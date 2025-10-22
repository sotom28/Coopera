package com.example.Coopera.Repository;

import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.MultaMoraId;
import com.example.Coopera.Modelo.Multa_Mora;

@Repository
public interface MultaMoraRepository extends org.springframework.data.jpa.repository.JpaRepository<Multa_Mora, MultaMoraId> {
}
