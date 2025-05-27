package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Procedimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private Double valorProcedimento;

    @JsonBackReference("procedimentos-consulta")
    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @JsonBackReference("procedimentos-exame")
    @OneToOne
    @JoinColumn(name = "id_exame")
    private Exame exame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorProcedimento() {
        return valorProcedimento;
    }

    public void setValorProcedimento(Double valorProcedimento) {
        this.valorProcedimento = valorProcedimento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }
}
