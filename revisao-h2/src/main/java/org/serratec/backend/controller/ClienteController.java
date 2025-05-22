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
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@Valid @PathVariable UUID id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            return ResponseEntity.ok(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Cliente>> inserirListaClientes(@Valid @RequestBody List<Cliente> clientes){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.saveAll(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterarCliente(@Valid @PathVariable UUID id, @RequestBody Cliente cliente){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            cliente.setId(id);
            cliente.setNome(cliente.getNome());
            cliente.setEmail(cliente.getEmail());
            clienteRepository.save(cliente);

            return ResponseEntity.ok().body(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@Valid @PathVariable UUID id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}