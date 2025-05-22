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
public class Diretor extends Funcionario{
    private Long id;
    private String nivelHierarquico;

    @JsonManagedReference("diretor-funcionario")
    @OneToMany(mappedBy = "diretor")
    private List<Funcionario> funcionarios;
}
