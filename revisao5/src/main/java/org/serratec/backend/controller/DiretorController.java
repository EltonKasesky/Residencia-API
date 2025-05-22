package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Diretor;
import org.serratec.backend.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {
    @Autowired
    private DiretorRepository repository;

    @GetMapping
    public ResponseEntity<List<Diretor>> listar(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> listarPorId(@PathVariable Long id){
        Optional<Diretor> f = repository.findById(id);
        if(f.isPresent()){
            return ResponseEntity.ok().body(f.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Diretor> criar(@Valid @RequestBody Diretor diretor){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(diretor));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Diretor>> criarLista(@Valid @RequestBody List<Diretor> diretores){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.saveAll(diretores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizar(@PathVariable Long id, @Valid @RequestBody Diretor diretor){
        Optional<Diretor> f = repository.findById(id);
        if(f.isPresent()){
            diretor.setId(id);
            return ResponseEntity.ok().body(repository.save(diretor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Diretor> deletar(@PathVariable Long id){
        Optional<Diretor> f = repository.findById(id);
        if(f.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}