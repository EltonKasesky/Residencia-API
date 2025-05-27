package org.serratec.backend.dto;

import org.serratec.backend.entity.Consulta;
import org.serratec.backend.entity.Procedimentos;

import java.time.LocalDate;
import java.util.List;

public class ConsultaDTO {
    private LocalDate dataConsulta;
    private String nomePaciente;
    private List<Procedimentos> procedimentosDTO;
    private Double totalGeral;

    public ConsultaDTO(Consulta consulta, Double totalGeral) {
        this.dataConsulta = consulta.getDataConsulta();
        this.nomePaciente = consulta.getPaciente().getNome();
        this.procedimentosDTO = consulta.getProcedimentos();
        this.totalGeral = totalGeral;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public List<Procedimentos> getProcedimentosDTO() {
        return procedimentosDTO;
    }

    public void setProcedimentosDTO(List<Procedimentos> procedimentosDTO) {
        this.procedimentosDTO = procedimentosDTO;
    }

    public Double getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(Double totalGeral) {
        this.totalGeral = totalGeral;
    }
}
