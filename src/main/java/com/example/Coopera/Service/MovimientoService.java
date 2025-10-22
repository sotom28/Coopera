package com.example.Coopera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Coopera.Modelo.Movimiento;
import com.example.Coopera.Repository.MovimientoRepository;

@Service
public class MovimientoService {
    private final MovimientoRepository repo;
    public MovimientoService(MovimientoRepository repo){ this.repo = repo; }
    public List<Movimiento> findAll(){ return repo.findAll(); }
    public Optional<Movimiento> findById(Long id){ return repo.findById(id); }
    public Movimiento save(Movimiento m){ return repo.save(m); }
    public void deleteById(Long id){ repo.deleteById(id); }
}
