package org.serratec.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    @Size(min = 3, max = 60, message = "Tamanho de caracteres incorreto para o nome!")
    private String nome;

    @Email(message = "Esse formato de email não é valido. Tente um email valido!")
    private String email;

    @CPF(message = "Esse formado de CPF não é valido. Tente um CPF valido!")
    private String cpf;

    @PastOrPresent(message = "Somente é aceito datas presentes ou passadas!")
    private LocalDate dataNascimento;
}