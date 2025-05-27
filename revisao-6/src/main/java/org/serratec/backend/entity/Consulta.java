package org.serratec.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataConsulta;

    @JsonBackReference("consulta-paciente")
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @JsonBackReference("consulta-medico")
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @JsonManagedReference("procedimentos-consulta")
    @OneToMany(mappedBy = "consulta")
    private List<Procedimentos> procedimentos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Procedimentos> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimentos> procedimentos) {
        this.procedimentos = procedimentos;
    }
}
