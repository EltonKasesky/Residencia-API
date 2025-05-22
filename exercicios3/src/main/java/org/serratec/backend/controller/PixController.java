package org.serratec.backend.controller;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Pix;
import org.serratec.backend.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/pix")
public class PixController {
    @Autowired
    private PixService service;

    @GetMapping
    public ResponseEntity<List<Pix>> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @GetMapping("/origem/{id}")
    public ResponseEntity<List<Pix>> listarPorOrigem(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorOrigem(id));
    }

    @GetMapping("/destino/{id}")
    public ResponseEntity<List<Pix>> listarPorDestino(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorDestino(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pix> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pix> enviarPix(@RequestBody Pix pix){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.enviarPix(pix));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pix> cancelarPix(@PathVariable Long id){
        return ResponseEntity.ok().body(service.cancelarPix(id));
    }
}
