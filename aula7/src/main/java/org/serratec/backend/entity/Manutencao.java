package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String obs;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @ManyToMany
    @JoinTable(
            name = "manutencao_servico",
            joinColumns = @JoinColumn(name = "id_manutencao"),
            inverseJoinColumns = @JoinColumn(name = "id_servico")
    )
    private List<Servico> servicos;
}
