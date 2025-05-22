package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable UUID id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            return ResponseEntity.ok(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteRepository.save(cliente));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Cliente>> inserirListaClientes(@RequestBody List<Cliente> clientes){
        return ResponseEntity.ok().body(clienteRepository.saveAll(clientes));
    }
}
