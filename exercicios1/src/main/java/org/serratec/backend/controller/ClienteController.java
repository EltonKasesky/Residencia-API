package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok().body(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable Long id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            return ResponseEntity.ok().body(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Cliente>> cadastrarListaClientes(@Valid @RequestBody List<Cliente> clientes){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.saveAll(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            cliente.setId(id);
            return ResponseEntity.ok().body(clienteRepository.save(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
