package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Gerente;
import org.serratec.backend.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {
    @Autowired
    private GerenteRepository repository;

    @GetMapping
    public ResponseEntity<List<Gerente>> listar(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> listarPorId(@PathVariable Long id){
        Optional<Gerente> f = repository.findById(id);
        if(f.isPresent()){
            return ResponseEntity.ok().body(f.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gerente> criar(@Valid @RequestBody Gerente gerente){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(gerente));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Gerente>> criarLista(@Valid @RequestBody List<Gerente> gerentes){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.saveAll(gerentes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizar(@PathVariable Long id, @Valid @RequestBody Gerente gerente){
        Optional<Gerente> f = repository.findById(id);
        if(f.isPresent()){
            gerente.setId(id);
            return ResponseEntity.ok().body(repository.save(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gerente> deletar(@PathVariable Long id){
        Optional<Gerente> f = repository.findById(id);
        if(f.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}