package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.entity.Endereco;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos(){
        return ResponseEntity.ok().body(enderecoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> listarPorId(@PathVariable Long id){
        Optional<Endereco> e = enderecoRepository.findById(id);
        if(e.isPresent()){
            return ResponseEntity.ok().body(e.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@Valid @RequestBody Endereco endereco){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Endereco>> cadastrarListaEnderecos(@Valid @RequestBody List<Endereco> enderecos){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.saveAll(enderecos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Endereco endereco){
        Optional<Endereco> c = enderecoRepository.findById(id);
        if(c.isPresent()){
            endereco.setId(id);
            return ResponseEntity.ok().body(enderecoRepository.save(endereco));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletarEndereco(@PathVariable Long id){
        Optional<Endereco> c = enderecoRepository.findById(id);
        if(c.isPresent()){
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
