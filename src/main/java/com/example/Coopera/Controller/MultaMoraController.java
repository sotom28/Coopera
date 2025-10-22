package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Multa_Mora;
import com.example.Coopera.Modelo.MultaMoraId;
import com.example.Coopera.Service.MultaMoraService;

@RestController
@RequestMapping("/api/multamora")
public class MultaMoraController {

    private final MultaMoraService service;
    public MultaMoraController(MultaMoraService service){ this.service = service; }

    @GetMapping
    public List<Multa_Mora> all(){ return service.findAll(); }

    // GET by composite key: /api/multamora?min=1&max=10
    @GetMapping(params = {"min","max"})
    public ResponseEntity<Multa_Mora> getByKeys(@RequestParam("min") Integer min, @RequestParam("max") Integer max){
        MultaMoraId id = new MultaMoraId(min, max);
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Multa_Mora create(@RequestBody Multa_Mora m){ return service.save(m); }

    @DeleteMapping(params = {"min","max"})
    public ResponseEntity<Void> delete(@RequestParam("min") Integer min, @RequestParam("max") Integer max){
        MultaMoraId id = new MultaMoraId(min, max);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
