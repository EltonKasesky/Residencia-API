package org.serratec.backend.service;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository repository;

    public List<Conta> listar(){
        return repository.findAll();
    }

    public Conta listarPorId(Long id){
        Optional<Conta> c = repository.findById(id);
        if(c.isPresent()){
            return c.get();
        }
        throw new ContaException("ID não encontrado.");
    }

    public Conta criar(Conta conta){
        if(repository.findByCpf(conta.getCpf()).isPresent()){
            throw new ContaException("Já existe uma conta cadastrada nesse CPF.");
        }

        if(conta.getSaldo() > 0){
            return repository.save(conta);
        }
        throw new ContaException("Saldo insuficiente.");
    }

    public List<Conta> criarLista(List<Conta> contas){
        for(Conta c : contas){
            if(repository.findByCpf(c.getCpf()).isPresent()){
                throw new ContaException("Já existe uma conta cadastrada nesse CPF.");
            }

            if(c.getSaldo() > 0){
                return repository.saveAll(contas);
            }
            throw new ContaException("Saldo insuficiente.");
        }
        throw new ContaException("Não foi encontrada uma lista valida!");
    }

    public Conta atualizar(Long id, Conta conta){
        Optional<Conta> c = repository.findById(id);
        if(c.isPresent()){
            conta.setId(id);
            return repository.save(conta);
        }
        throw new ContaException("ID não encontrado.");
    }

    public Void deletar(Long id){
        Optional<Conta> c = repository.findById(id);
        if(c.isPresent()){
            repository.deleteById(id);
        }
        throw new ContaException("ID não encontrado.");
    }
}
