package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Pago_Mensual_Credito;
import com.example.Coopera.Repository.PagoMensualCreditoRepository;

@RestController
@RequestMapping("/api/pagos-mensuales")
public class PagoMensualCreditoController {

    private final PagoMensualCreditoRepository repo;
    public PagoMensualCreditoController(PagoMensualCreditoRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Pago_Mensual_Credito> list(){ return repo.findAll(); }

    @GetMapping("/{fecha}")
    public ResponseEntity<Pago_Mensual_Credito> get(@PathVariable String fecha){
        return repo.findById(fecha).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pago_Mensual_Credito create(@RequestBody Pago_Mensual_Credito p){ return repo.save(p); }

    @PutMapping("/{fecha}")
    public ResponseEntity<Pago_Mensual_Credito> update(@PathVariable String fecha, @RequestBody Pago_Mensual_Credito p){
        return repo.findById(fecha).map(existing -> { return ResponseEntity.ok(repo.save(p)); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{fecha}")
    public ResponseEntity<Void> delete(@PathVariable String fecha){ repo.deleteById(fecha); return ResponseEntity.noContent().build(); }
}
