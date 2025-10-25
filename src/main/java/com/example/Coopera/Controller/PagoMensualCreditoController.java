package com.example.Coopera.Controller;

import java.util.List;

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

    @PostMapping
    public Pago_Mensual_Credito create(@RequestBody Pago_Mensual_Credito p){ return repo.save(p); }

    
    // Nota: para identificar un registro específico ahora se requiere la clave compuesta
    // (fechaProceso, nroSocio, nroSolicCredito, nroCuotaMes). Si necesitas endpoints
    // por ID, los agregamos con esos 4 parámetros en la ruta.
}
