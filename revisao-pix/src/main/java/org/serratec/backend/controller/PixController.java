package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.dto.PixRequestDTO;
import org.serratec.backend.dto.PixResponseDTO;
import org.serratec.backend.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pix")
public class PixController {
    @Autowired
    private PixService service;

    @GetMapping
    public ResponseEntity<List<PixResponseDTO>> listarPix(){
        return ResponseEntity.ok().body(service.listarPix());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PixResponseDTO> enviarPix(@Valid @RequestBody PixRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.enviarPix(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PixResponseDTO> cancelarPix(@PathVariable Long id){
        return ResponseEntity.ok().body(service.cancelarPix(id));
    }
}
