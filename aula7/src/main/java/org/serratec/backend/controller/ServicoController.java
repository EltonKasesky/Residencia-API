package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Servico;
import org.serratec.backend.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> listarPorId(@PathVariable Long id){
        Optional<Servico> s = servicoRepository.findById(id);
        if(s.isPresent()){
            return ResponseEntity.ok(s.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Servico> cadastrarServico(@Valid @RequestBody Servico servico){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoRepository.save(servico));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Servico>> cadastrarListaServicos(@Valid @RequestBody List<Servico> servicos){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoRepository.saveAll(servicos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> alterarServico(@PathVariable Long id, @Valid @RequestBody Servico servico){
        Optional<Servico> s = servicoRepository.findById(id);
        if(s.isPresent()){
            servico.setId(id);
            servicoRepository.save(servico);

            return ResponseEntity.ok().body(s.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id){
        Optional<Servico> s = servicoRepository.findById(id);
        if(s.isPresent()){
            servicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
