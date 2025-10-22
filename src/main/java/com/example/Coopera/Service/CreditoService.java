package com.example.Coopera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Coopera.Modelo.Credito;
import com.example.Coopera.Repository.CreditoRepository;

@Service
public class CreditoService {
    private final CreditoRepository repo;
    public CreditoService(CreditoRepository repo) { this.repo = repo; }
    public List<Credito> findAll(){ return repo.findAll(); }
    public Optional<Credito> findById(Integer id){ return repo.findById(id); }
    public Credito save(Credito c){ return repo.save(c); }
    public void deleteById(Integer id){ repo.deleteById(id); }
}
