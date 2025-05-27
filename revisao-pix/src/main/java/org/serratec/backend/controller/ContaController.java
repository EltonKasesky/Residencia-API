package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.dto.ContaRequestDTO;
import org.serratec.backend.dto.ContaResponseDTO;
import org.serratec.backend.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @Autowired
    private ContaService service;

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> listarContas(){
        return ResponseEntity.ok().body(service.listarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> adicionarConta(@Valid @RequestBody ContaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionarConta(dto));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<ContaResponseDTO>> adicionarListaContas(@Valid @RequestBody List<ContaRequestDTO> dtos){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionarListaContas(dtos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> atualizarConta(@PathVariable Long id, @Valid @RequestBody ContaRequestDTO dto){
        return ResponseEntity.ok().body(service.atualizarConta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id){
        service.deletarConta(id);
        return ResponseEntity.noContent().build();
    }
}
