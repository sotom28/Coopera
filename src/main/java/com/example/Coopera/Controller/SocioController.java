package com.example.Coopera.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Coopera.Modelo.Socio;
import com.example.Coopera.Modelo.Usuario_Clave;
import com.example.Coopera.Service.SocioService;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService service;

    public SocioController(SocioService service) {
        this.service = service;
    }

    @GetMapping("/panel/socios")
    public String panelSocios(Model model) {
        // La vista hará fetch a /api/v2/socios; evitamos cargar datos pesados aquí
        return "panel-socios";
    }

    @GetMapping
    public List<Socio> list() {
        return service.findAll();
    }

    @GetMapping("/count")
    public Map<String, Long> count(){
        Map<String, Long> r = new HashMap<>();
        r.put("count", service.findAll().stream().count());
        return r;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Socio> create(@RequestBody Socio socio) {
        Socio saved = service.save(socio);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Crea socio y además genera usuario_clave automáticamente
    @PostMapping("/create-with-user")
    public ResponseEntity<Map<String, Object>> createWithUser(@RequestBody Socio socio) {
        try {
            Usuario_Clave uc = service.saveSocioAndCreateUsuarioClave(socio);
            Map<String, Object> body = new HashMap<>();
            // recuperar el socio utilizando el id asignado en usuario_clave
            Socio savedSocio = (uc != null && uc.getNroSocio() != null)
                    ? service.findById(uc.getNroSocio().longValue()).orElse(socio)
                    : socio;
            body.put("socio", savedSocio);
            body.put("usuarioClave", uc);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "No se pudo crear el socio");
            error.put("detail", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> update(@PathVariable Long id, @RequestBody Socio socio) {
        return service.findById(id).map(existing -> {
            socio.setNumeroSocio(id);
            Socio updated = service.save(socio);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
