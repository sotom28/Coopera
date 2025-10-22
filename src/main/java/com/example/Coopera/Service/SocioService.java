package com.example.Coopera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Coopera.Modelo.Socio;
import com.example.Coopera.Repository.SocioRepository;

@Service
public class SocioService {

    private final SocioRepository repo;

    public SocioService(SocioRepository repo) {
        this.repo = repo;
    }

    public List<Socio> findAll() {
        return repo.findAll();
    }

    public Optional<Socio> findById(Long id) {
        return repo.findById(id);
    }

    public Socio save(Socio socio) {
        return repo.save(socio);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
