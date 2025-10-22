package com.example.Coopera.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Coopera.Modelo.Usuario_Clave;
import com.example.Coopera.Repository.UsuarioClaveRepository;

@RestController
@RequestMapping("/api/usuario-clave")
public class UsuarioClaveController {

    private final UsuarioClaveRepository repo;
    public UsuarioClaveController(UsuarioClaveRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Usuario_Clave> list(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario_Clave> get(@PathVariable Integer id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario_Clave create(@RequestBody Usuario_Clave u){ return repo.save(u); }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario_Clave> update(@PathVariable Integer id, @RequestBody Usuario_Clave u){
        return repo.findById(id).map(existing -> { return ResponseEntity.ok(repo.save(u)); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
