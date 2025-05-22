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
    private DiretorRepository diretorRepository;

    @GetMapping
    public ResponseEntity<List<Diretor>> listarDiretores(){
        return ResponseEntity.ok().body(diretorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> listarPorId(@PathVariable Long id){
        Optional<Diretor> d = diretorRepository.findById(id);
        if(d.isPresent()){
            return ResponseEntity.ok().body(d.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Diretor> cadastrarDiretor(@Valid @RequestBody Diretor diretor){
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorRepository.save(diretor));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Diretor>> cadastrarListaDiretores(@Valid @RequestBody List<Diretor> diretores){
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorRepository.saveAll(diretores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @Valid @RequestBody Diretor diretor){
        Optional<Diretor> d = diretorRepository.findById(id);
        if(d.isPresent()){
            diretor.setId(id);
            return ResponseEntity.ok().body(diretorRepository.save(diretor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Diretor> deletarDiretor(@PathVariable Long id){
        Optional<Diretor> d = diretorRepository.findById(id);
        if(d.isPresent()){
            diretorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
