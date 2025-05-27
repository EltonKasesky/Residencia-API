package org.serratec.backend.controller;

import org.serratec.backend.dto.ConsultaDTO;
import org.serratec.backend.entity.Consulta;
import org.serratec.backend.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarConsultas(){
        return ResponseEntity.ok().body(service.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> salvarConsulta(@RequestBody Consulta consulta){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarConsulta(consulta));
    }
}
