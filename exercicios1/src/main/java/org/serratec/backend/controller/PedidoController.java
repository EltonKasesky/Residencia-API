package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.ok().body(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> listarPorId(@PathVariable Long id){
        Optional<Pedido> p = pedidoRepository.findById(id);
        if(p.isPresent()){
            return ResponseEntity.ok().body(p.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@Valid @RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Pedido>> cadastrarListaPedidos(@Valid @RequestBody List<Pedido> pedidos){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.saveAll(pedidos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedido){
        Optional<Pedido> c = pedidoRepository.findById(id);
        if(c.isPresent()){
            pedido.setId(id);
            return ResponseEntity.ok().body(pedidoRepository.save(pedido));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> deletarPedido(@PathVariable Long id){
        Optional<Pedido> c = pedidoRepository.findById(id);
        if(c.isPresent()){
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
