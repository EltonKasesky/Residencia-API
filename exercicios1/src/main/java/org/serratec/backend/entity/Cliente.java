package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente não pode ser vazio ou nulo!")
    private String nome;

    @CPF
    @NotBlank(message = "O cpf do cliente não pode ser vazio ou nulo!")
    private String cpf;

    @Email
    @NotBlank(message = "O email do cliente não pode ser vazio ou nulo!")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @JsonManagedReference
    @OneToOne(mappedBy = "cliente")
    private Endereco endereco;
}
