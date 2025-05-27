package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTitular;
    private String email;
    private String senha;
    private Double saldo;

    @JsonManagedReference("origem-conta")
    @OneToMany(mappedBy = "contaOrigem")
    private List<Pix> pixEnviados;

    @JsonManagedReference("destino-conta")
    @OneToMany(mappedBy = "contaDestino")
    private List<Pix> pixRecebidos;
}
