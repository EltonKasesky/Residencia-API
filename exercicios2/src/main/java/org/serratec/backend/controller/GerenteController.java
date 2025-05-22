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
    private GerenteRepository gerenteRepository;

    @GetMapping
    public ResponseEntity<List<Gerente>> listarGerentes(){
        return ResponseEntity.ok().body(gerenteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> listarPorId(@PathVariable Long id){
        Optional<Gerente> g = gerenteRepository.findById(id);
        if(g.isPresent()){
            return ResponseEntity.ok().body(g.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gerente> cadastrarGerente(@Valid @RequestBody Gerente gerente){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteRepository.save(gerente));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Gerente>> cadastrarListaGerentes(@Valid @RequestBody List<Gerente> gerentes){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteRepository.saveAll(gerentes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Long id, @Valid @RequestBody Gerente gerente){
        Optional<Gerente> g = gerenteRepository.findById(id);
        if(g.isPresent()){
            gerente.setId(id);
            return ResponseEntity.ok().body(gerenteRepository.save(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gerente> deletarGerente(@PathVariable Long id){
        Optional<Gerente> g = gerenteRepository.findById(id);
        if(g.isPresent()){
            gerenteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
