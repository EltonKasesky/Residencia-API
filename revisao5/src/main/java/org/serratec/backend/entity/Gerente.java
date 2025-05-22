package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Gerente extends Funcionario {
    private String tipo;

    @JsonManagedReference("gerente-funcionario")
    @OneToMany(mappedBy = "gerente")
    private List<Funcionario> funcionarios;
}
