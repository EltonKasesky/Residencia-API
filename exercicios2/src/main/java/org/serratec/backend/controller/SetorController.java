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
    private SetorRepository setorRepository;

    @GetMapping
    public ResponseEntity<List<Setor>> listarSetores(){
        return ResponseEntity.ok().body(setorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> listarPorId(@PathVariable Long id){
        Optional<Setor> s = setorRepository.findById(id);
        if(s.isPresent()){
            return ResponseEntity.ok().body(s.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrarSetor(@Valid @RequestBody Setor setor){
        return ResponseEntity.status(HttpStatus.CREATED).body(setorRepository.save(setor));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Setor>> cadastrarListaSetores(@Valid @RequestBody List<Setor> setores){
        return ResponseEntity.status(HttpStatus.CREATED).body(setorRepository.saveAll(setores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizarSetor(@PathVariable Long id, @Valid @RequestBody Setor setor){
        Optional<Setor> s = setorRepository.findById(id);
        if(s.isPresent()){
            setor.setId(id);
            return ResponseEntity.ok().body(setorRepository.save(setor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Setor> deletarSetor(@PathVariable Long id){
        Optional<Setor> s = setorRepository.findById(id);
        if(s.isPresent()){
            setorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
