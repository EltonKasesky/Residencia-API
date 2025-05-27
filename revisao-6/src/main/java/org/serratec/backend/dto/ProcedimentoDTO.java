package org.serratec.backend.dto;

import org.serratec.backend.entity.Procedimentos;

public class ProcedimentoDTO {
    private String tipoExame;
    private Integer quantidade;
    private Double valorProcedimento;
    private Double subtotal;

    public ProcedimentoDTO(Procedimentos procedimentos, Double subtotal) {
        this.tipoExame = procedimentos.getExame().getTipo();
        this.quantidade = procedimentos.getQuantidade();
        this.valorProcedimento = procedimentos.getValorProcedimento();
        this.subtotal = quantidade * valorProcedimento;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
