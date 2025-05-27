package org.serratec.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.entity.Conta;

@NoArgsConstructor
@Setter
@Getter
public class PixRequestDTO {
    @NotNull(message = "O valor do pix não pode ser nulo!")
    private Double valor;

    @NotNull(message = "A conta de origem não pode ser nula!")
    private Conta contaOrigem;

    @NotNull(message = "A conta de destino não pode ser nula!")
    private Conta contaDestino;
}
