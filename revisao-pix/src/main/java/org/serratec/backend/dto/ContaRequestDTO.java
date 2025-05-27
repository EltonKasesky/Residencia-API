package org.serratec.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ContaRequestDTO {
    @NotBlank(message = "O nome do titular não pode ser vazio ou nulo!")
    @Size(min = 2, message = "O nome do titular deve conter entre 2 e 32 caracteres")
    private String nomeTitular;

    @NotBlank(message = "O E-mail do titular não pode ser vazio ou nulo!")
    @Email(message = "O E-mail inserido tem uma formatação invalida!")
    private String email;

    @NotBlank(message = "A senha do titular não pode ser vazia ou nula!")
    @Size(min = 8, message = "A senha teve conter pelo menos 8 caracteres")
    private String senha;

    @NotNull(message = "O saldo do titular não pode ser nulo!")
    private Double saldo;
}
