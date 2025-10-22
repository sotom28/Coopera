package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Movimiento;
import com.example.Coopera.Service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService service;
    public MovimientoController(MovimientoService service){ this.service = service; }

    @GetMapping
    public List<Movimiento> all(){ return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> get(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movimiento create(@RequestBody Movimiento m){ return service.save(m); }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> update(@PathVariable Long id, @RequestBody Movimiento m){
        return service.findById(id).map(existing -> {
            
            return ResponseEntity.ok(service.save(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ service.deleteById(id); return ResponseEntity.noContent().build(); }
}
