package org.serratec.backend.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class PessoaFisica extends Fornecedor{
    private String cpf;
    private String rg;
    private String orgaoExpedidor;
}
