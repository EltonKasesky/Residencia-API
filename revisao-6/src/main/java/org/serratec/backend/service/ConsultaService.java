package org.serratec.backend.service;

import org.serratec.backend.dto.ConsultaDTO;
import org.serratec.backend.entity.Consulta;
import org.serratec.backend.entity.Procedimentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.serratec.backend.repository.ConsultaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repository;

    public List<ConsultaDTO> listarConsultas() {
        List<Consulta> consultas = repository.findAll();
        List<ConsultaDTO> consultasDTO = new ArrayList<>();

        for (Consulta consulta : consultas) {
            Double totalGeral = 0.0;

            for (Procedimentos procedimento : consulta.getProcedimentos()) {
                totalGeral += procedimento.getValorProcedimento() * procedimento.getQuantidade();
            }

            consultasDTO.add(new ConsultaDTO(
                    consulta,
                    totalGeral
            ));
        }
        return consultasDTO;
    }

    public ConsultaDTO listarPorId(Long id){
        Consulta c = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta não encontrada!"));

        Double totalGeral = 0.0;

        for(Procedimentos procedimento : c.getProcedimentos()){
            totalGeral += procedimento.getValorProcedimento() * procedimento.getQuantidade();
        }

        return new ConsultaDTO(c, totalGeral);
    }

    public ConsultaDTO salvarConsulta(Consulta consulta){
        Consulta c = repository.save(consulta);

        Double totalGeral = 0.0;

        for(Procedimentos procedimento : c.getProcedimentos()){
            totalGeral += procedimento.getValorProcedimento() * procedimento.getQuantidade();
        }

        return new ConsultaDTO(
                c,
                totalGeral
        );
    }
}
