package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Manutencao;
import org.serratec.backend.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {
    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @GetMapping
    public List<Manutencao> listarManutencoes(){
        return manutencaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> listarPorId(@PathVariable Long id){
        Optional<Manutencao> m = manutencaoRepository.findById(id);
        if(m.isPresent()){
            return ResponseEntity.ok(m.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Manutencao> cadastrarManutencao(@Valid @RequestBody Manutencao manutencao){
        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoRepository.save(manutencao));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Manutencao>> cadastrarListaManutencoes(@Valid @RequestBody List<Manutencao> manutencoes){
        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoRepository.saveAll(manutencoes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> alterarManutencao(@PathVariable Long id, @Valid @RequestBody Manutencao manutencao){
        Optional<Manutencao> m = manutencaoRepository.findById(id);
        if(m.isPresent()){
            manutencao.setId(id);
            manutencaoRepository.save(manutencao);

            return ResponseEntity.ok().body(m.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarManutencao(@PathVariable Long id){
        Optional<Manutencao> m = manutencaoRepository.findById(id);
        if(m.isPresent()){
            manutencaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
