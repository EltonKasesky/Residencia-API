package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    @CPF(message = "Deve ser inserido um CPF valido!")
    @Size(max = 11, min = 11, message = "O CPF deve conter 11 caracteres!")
    protected String cpf;
    protected Double salario;
    protected String turno;

    @JsonBackReference("setor-funcionario")
    @ManyToOne
    @JoinColumn(name = "id_setor")
    protected Setor setor;

    @JsonBackReference("gerente-funcionario")
    @ManyToOne
    @JoinColumn(name = "id_gerente")
    protected Gerente gerente;

    @JsonBackReference("diretor-funcionario")
    @ManyToOne
    @JoinColumn(name = "id_diretor")
    protected Diretor diretor;
}
