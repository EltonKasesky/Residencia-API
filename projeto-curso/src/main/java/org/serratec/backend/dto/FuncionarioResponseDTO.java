package org.serratec.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.serratec.backend.entity.Funcionario;

import java.time.LocalDate;

@Setter
@Getter
public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Double salario;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.dataNascimento = funcionario.getDataNascimento();
        this.salario = funcionario.getSalario();
    }
}
