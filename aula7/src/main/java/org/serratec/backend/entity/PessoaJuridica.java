package org.serratec.backend.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PessoaJuridica extends Fornecedor {
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
}
