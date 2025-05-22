package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Size(min = 3, max = 60)
    private String nome;

    @CPF(message = "Foi inserido um CPF invalido. Tente novamente com um CPF valido!")
    @Column(updatable = false)
    private String cpf;

    @Email(message = "Foi inserido um padrão de email invalido. Tente novamente com um email valido!")
    private String email;

    @PastOrPresent
    @Column(updatable = false)
    private LocalDate dataNascimento;
}