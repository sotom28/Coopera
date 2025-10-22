package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Credito;
import com.example.Coopera.Service.CreditoService;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    private final CreditoService service;
    public CreditoController(CreditoService service){ this.service = service; }

    @GetMapping
    public List<Credito> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Credito> get(@PathVariable Integer id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Credito create(@RequestBody Credito c){ return service.save(c); }

    @PutMapping("/{id}")
    public ResponseEntity<Credito> update(@PathVariable Integer id, @RequestBody Credito c){
        return service.findById(id).map(existing -> {
            // copy mutable fields
            existing.setNombreCredito(c.getNombreCredito());
            existing.setDescCredito(c.getDescCredito());
            existing.setTasaInteresAnual(c.getTasaInteresAnual());
            existing.setNroMaximoCuotas(c.getNroMaximoCuotas());
            return ResponseEntity.ok(service.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
