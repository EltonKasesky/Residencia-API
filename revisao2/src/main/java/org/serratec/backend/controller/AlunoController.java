package org.serratec.backend.controller;

import org.serratec.backend.entity.Aluno;
import org.serratec.backend.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> listarPorId(@PathVariable Long id){
        Optional<Aluno> a = alunoRepository.findById(id);
        if(a.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a.get());
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoRepository.save(aluno));
    }

    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Aluno> cadastrarListaAlunos(@RequestBody List<Aluno> alunos){
        return alunoRepository.saveAll(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno){
        Optional<Aluno> a = alunoRepository.findById(id);
        if(a.isPresent()){
            aluno.setId(id);
            return ResponseEntity.ok(alunoRepository.save(aluno));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id){
       Optional<Aluno> a = alunoRepository.findById(id);
       if(a.isPresent()){
           alunoRepository.deleteById(id);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }
}
