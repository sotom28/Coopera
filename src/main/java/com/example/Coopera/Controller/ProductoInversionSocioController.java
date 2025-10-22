package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Producto_Inversion_socio;
import com.example.Coopera.Repository.ProductoInversionSocioRepository;

@RestController
@RequestMapping("/api/producto-inversion-socios")
public class ProductoInversionSocioController {

    private final ProductoInversionSocioRepository repo;
    public ProductoInversionSocioController(ProductoInversionSocioRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Producto_Inversion_socio> list(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Producto_Inversion_socio> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto_Inversion_socio create(@RequestBody Producto_Inversion_socio p){ return repo.save(p); }

    @PutMapping("/{id}")
    public ResponseEntity<Producto_Inversion_socio> update(@PathVariable Long id, @RequestBody Producto_Inversion_socio p){
        return repo.findById(id).map(existing -> { return ResponseEntity.ok(repo.save(p)); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
