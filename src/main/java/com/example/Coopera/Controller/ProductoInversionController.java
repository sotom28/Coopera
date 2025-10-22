package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Producto_Inversion;
import com.example.Coopera.Service.ProductoInversionService;

@RestController
@RequestMapping("/api/productos")
public class ProductoInversionController {

    private final ProductoInversionService service;
    public ProductoInversionController(ProductoInversionService service){ this.service = service; }

    @GetMapping
    public List<Producto_Inversion> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Producto_Inversion> get(@PathVariable Integer id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto_Inversion create(@RequestBody Producto_Inversion p){ return service.save(p); }

    @PutMapping("/{id}")
    public ResponseEntity<Producto_Inversion> update(@PathVariable Integer id, @RequestBody Producto_Inversion p){
        return service.findById(id).map(existing -> { return ResponseEntity.ok(service.save(p)); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){ service.deleteById(id); return ResponseEntity.noContent().build(); }
}
