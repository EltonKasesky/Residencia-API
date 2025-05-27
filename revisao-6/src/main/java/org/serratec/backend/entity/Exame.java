package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "exame")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Double valor;

    @JsonManagedReference("procedimentos-exame")
    @OneToOne(mappedBy = "exame")
    private Procedimentos procedimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Procedimentos getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimentos procedimento) {
        this.procedimento = procedimento;
    }
}
