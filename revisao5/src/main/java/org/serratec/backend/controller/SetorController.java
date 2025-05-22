package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Setor;
import org.serratec.backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setores")
public class SetorController {
    @Autowired
    private SetorRepository repository;

    @GetMapping
    public ResponseEntity<List<Setor>> listar(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> listarPorId(@PathVariable Long id){
        Optional<Setor> f = repository.findById(id);
        if(f.isPresent()){
            return ResponseEntity.ok().body(f.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Setor> criar(@Valid @RequestBody Setor setor){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(setor));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Setor>> criarLista(@Valid @RequestBody List<Setor> setores){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.saveAll(setores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(@PathVariable Long id, @Valid @RequestBody Setor setor){
        Optional<Setor> f = repository.findById(id);
        if(f.isPresent()){
            setor.setId(id);
            return ResponseEntity.ok().body(repository.save(setor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Setor> deletar(@PathVariable Long id){
        Optional<Setor> f = repository.findById(id);
        if(f.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}