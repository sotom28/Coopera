package com.example.Coopera.Service;


import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;



import com.example.Coopera.Modelo.Multa_Mora;
import com.example.Coopera.Modelo.MultaMoraId;
import com.example.Coopera.Repository.MultaMoraRepository;

@Service
public class MultaMoraService {
    private final MultaMoraRepository repo;
    public MultaMoraService(MultaMoraRepository repo){ this.repo = repo; }
    public List<Multa_Mora> findAll(){ return repo.findAll(); }
    public Optional<Multa_Mora> findById(MultaMoraId id){
        return repo.findById(id);
    }
    public Multa_Mora save(Multa_Mora m){
        return repo.save(m);
    }
    public void deleteById(MultaMoraId id){
        repo.deleteById(id);
    }
    
}
