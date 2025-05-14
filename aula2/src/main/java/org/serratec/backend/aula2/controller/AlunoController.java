package org.serratec.backend.aula2.controller;

import org.serratec.backend.aula2.entity.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private static List<Aluno> alunos = new ArrayList<>();

    static {
        alunos.add(new Aluno(123L, "Elton Kasesky", "eltonkasesky@gmail.com"));
        alunos.add(new Aluno(234L, "Ana Julia", "anajulia@gmail.com"));
        alunos.add(new Aluno(345L, "Inajara Eiras", "inajaraeiras@gmail.com"));
        alunos.add(new Aluno(456L, "Elcio Kasesky", "elciokasesky@gmail.com"));
    }

    @GetMapping
    public List<Aluno> listar(){
        return alunos;
    }

    @GetMapping("/{matricula}")
    public Aluno listarAlunoByMatricula(@PathVariable Long matricula){
        return alunos.stream()
                .filter(aluno -> aluno.getMatricula().equals(matricula))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));
    }

    @PostMapping
    public Aluno inserirAluno(@RequestBody Aluno aluno){
        boolean exist = alunos.stream()
                .anyMatch(a -> a.getMatricula().equals(aluno.getMatricula()));
        if(!exist){
            alunos.add(aluno);
            return aluno;
        } else {
            throw new RuntimeException("Aluno existente");
        }
    }

    @PostMapping("/lista")
    public List<Aluno> inserirListaDeAlunos(@RequestBody List<Aluno> alunos){
        AlunoController.alunos.addAll(alunos);
        return alunos;
    }

    @PutMapping("/{matricula}")
    public Aluno atualizarAluno(@PathVariable Long matricula, @RequestBody Aluno aluno){
        alunos.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst()
                .ifPresent(a -> a.setEmail(aluno.getEmail()));
        return aluno;
    }

    @DeleteMapping("/{matricula}")
    public void deletarAluno(@PathVariable Long matricula){
        alunos.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst()
                .ifPresent(a -> alunos.remove(a));
    }
}