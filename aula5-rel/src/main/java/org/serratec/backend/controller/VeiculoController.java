package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.entity.Veiculo;
import org.serratec.backend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listarVeiculos(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> listarPorId(@PathVariable Long id){
        Optional<Veiculo> c = veiculoRepository.findById(id);
        if(c.isPresent()){
            return ResponseEntity.ok(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo){
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.save(veiculo));
    }

    @PostMapping("/lista")
    public ResponseEntity<List<Veiculo>> cadastrarListaVeiculos(@Valid @RequestBody List<Veiculo> veiculos){
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.saveAll(veiculos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> alterarVeiculo(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo){
        Optional<Veiculo> c = veiculoRepository.findById(id);
        if(c.isPresent()){
            veiculo.setId(id);
            veiculoRepository.save(veiculo);

            return ResponseEntity.ok().body(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id){
        Optional<Veiculo> c = veiculoRepository.findById(id);
        if(c.isPresent()){
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
