package org.serratec.backend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.serratec.backend.enums.Categoria;
import org.serratec.backend.enums.Combustivel;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Caracteristica {
    private String renavam;
    private String chassi;
    private String cor;
    private Integer ano;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.ORDINAL)
    private Combustivel combustivel;
}
