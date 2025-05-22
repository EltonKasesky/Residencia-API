package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Proprietario;
import org.serratec.backend.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listarProprietarios(){
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> listarPorId(@PathVariable Long id){
        Optional<Proprietario> p = proprietarioRepository.findById(id);
        if(p.isPresent()){
            return ResponseEntity.ok(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proprietario> cadastrarProprietario(@Valid @RequestBody Proprietario proprietario){
        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioRepository.save(proprietario));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Proprietario>> cadastrarListaProprietarios(@Valid @RequestBody List<Proprietario> proprietarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioRepository.saveAll(proprietarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> alterarVeiculo(@PathVariable Long id, @Valid @RequestBody Proprietario proprietario){
        Optional<Proprietario> p = proprietarioRepository.findById(id);
        if(p.isPresent()){
            proprietario.setId(id);
            proprietarioRepository.save(proprietario);

            return ResponseEntity.ok().body(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id){
        Optional<Proprietario> p = proprietarioRepository.findById(id);
        if(p.isPresent()){
            proprietarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
