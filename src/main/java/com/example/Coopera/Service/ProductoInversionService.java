package com.example.Coopera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Coopera.Modelo.Producto_Inversion;
import com.example.Coopera.Repository.ProductoInversionRepository;

@Service
public class ProductoInversionService {
    private final ProductoInversionRepository repo;
    public ProductoInversionService(ProductoInversionRepository repo){ this.repo = repo; }
    public List<Producto_Inversion> findAll(){ return repo.findAll(); }
    public Optional<Producto_Inversion> findById(Integer id){ return repo.findById(id); }
    public Producto_Inversion save(Producto_Inversion p){ return repo.save(p); }
    public void deleteById(Integer id){ repo.deleteById(id); }
}
