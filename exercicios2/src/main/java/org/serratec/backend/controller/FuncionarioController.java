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
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        return ResponseEntity.ok().body(funcionarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> listarPorId(@PathVariable Long id){
        Optional<Funcionario> f = funcionarioRepository.findById(id);
        if(f.isPresent()){
            return ResponseEntity.ok().body(f.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@Valid @RequestBody Funcionario funcionario){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionario));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Funcionario>> cadastrarListaFuncionarios(@Valid @RequestBody List<Funcionario> funcionarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.saveAll(funcionarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario){
        Optional<Funcionario> f = funcionarioRepository.findById(id);
        if(f.isPresent()){
            funcionario.setId(id);
            return ResponseEntity.ok().body(funcionarioRepository.save(funcionario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deletarFuncionario(@PathVariable Long id){
        Optional<Funcionario> f = funcionarioRepository.findById(id);
        if(f.isPresent()){
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
