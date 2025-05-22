package org.serratec.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 7, max = 7, message = "A placa deve conter 7 caracteres, verifique a contagem dos caracteres na requisição, e tente novamente!")
    private String placa;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @Embedded
    private Caracteristica caracteristica;
}
