package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Gerente extends Funcionario {
    private Long id;
    private String tipo;

    @JsonManagedReference("gerente-funcionario")
    @OneToMany(mappedBy = "gerente")
    private List<Funcionario> funcionarios;
}
