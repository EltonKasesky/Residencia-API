package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Pix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private Double valor;
    private Boolean status;

    @JsonBackReference("origem-conta")
    @ManyToOne
    @JoinColumn(name = "id_conta_origem")
    private Conta contaOrigem;

    @JsonBackReference("destino-conta")
    @ManyToOne
    @JoinColumn(name = "id_conta_destino")
    private Conta contaDestino;
}
