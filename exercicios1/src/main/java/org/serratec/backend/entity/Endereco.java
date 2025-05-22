package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A rua referente ao endereço não pode ser nula ou vazia!")
    private String rua;

    @NotBlank(message = "O número referente ao endereço não pode ser nulo ou vazio!")
    private String numero;

    @NotBlank(message = "O complemento referente ao endereço não pode ser nulo ou vazio!")
    private String complemento;

    @NotBlank(message = "O bairro referente ao endereço não pode ser nulo ou vazio!")
    private String bairro;

    @NotBlank(message = "A cidade referente ao endereço não pode ser nulo ou vazio!")
    private String cidade;

    @NotBlank(message = "O estado referente ao endereço não pode ser nulo ou vazio!")
    private String estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
