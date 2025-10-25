package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Cuota_Credito_socio;
import com.example.Coopera.Modelo.Cuota_Credito_socio_Id;
import com.example.Coopera.Repository.CuotaCreditoSocioRepository;

@RestController
@RequestMapping("/api/cuotas-credito")
public class CuotaCreditoSocioController {

    private final CuotaCreditoSocioRepository repo;
    public CuotaCreditoSocioController(CuotaCreditoSocioRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Cuota_Credito_socio> list(){ return repo.findAll(); }

    @GetMapping("/{nroSolicCredito}/{nroCuota}")
    public ResponseEntity<Cuota_Credito_socio> get(
        @PathVariable Long nroSolicCredito,
        @PathVariable Integer nroCuota
    ){
        Cuota_Credito_socio_Id id = new Cuota_Credito_socio_Id();
        id.setNroSolicCredito(nroSolicCredito);
        id.setNroCuota(nroCuota);
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
