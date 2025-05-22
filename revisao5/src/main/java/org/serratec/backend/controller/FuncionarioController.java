package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> listarPorId(@PathVariable Long id){
        Optional<Funcionario> f = repository.findById(id);
        if(f.isPresent()){
            return ResponseEntity.ok().body(f.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> criar(@Valid @RequestBody Funcionario funcionario){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(funcionario));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Funcionario>> criarLista(@Valid @RequestBody List<Funcionario> funcionarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.saveAll(funcionarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario){
        Optional<Funcionario> f = repository.findById(id);
        if(f.isPresent()){
            funcionario.setId(id);
            return ResponseEntity.ok().body(repository.save(funcionario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deletar(@PathVariable Long id){
        Optional<Funcionario> f = repository.findById(id);
        if(f.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}