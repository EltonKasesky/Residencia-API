package org.serratec.backend.controller;

import org.serratec.backend.entity.Conta;
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
    public ResponseEntity<List<Conta>> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(conta));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Conta>> criarLista(@RequestBody List<Conta> contas){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarLista(contas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody Conta conta){
        return ResponseEntity.ok().body(service.atualizar(id, conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
