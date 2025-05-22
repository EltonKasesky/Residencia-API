package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroConta;
    private String nomeTitular;

    @CPF(message = "O CPF inserido é invalido. Tente novamente!")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres!")
    private String cpf;
    private Double saldo;

    @JsonManagedReference("pix-contaOrigem")
    @OneToMany(mappedBy = "contaOrigem")
    private List<Pix> pixEnviados;

    @JsonManagedReference("pix-contaDestino")
    @OneToMany(mappedBy = "contaDestino")
    private List<Pix> pixRecebidos;
}
