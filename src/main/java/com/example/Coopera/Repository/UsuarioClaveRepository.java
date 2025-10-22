package com.example.Coopera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Coopera.Modelo.Usuario_Clave;

@Repository
public interface UsuarioClaveRepository extends JpaRepository<Usuario_Clave, Integer> {
}
