package org.serratec.backend.service;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Pix;
import org.serratec.backend.exception.PixException;
import org.serratec.backend.repository.ContaRepository;
import org.serratec.backend.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PixService {
    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<Pix> listar(){
        return pixRepository.findAll();
    }

    public List<Pix> listarPorOrigem(Long id){
        Optional<Conta> c = contaRepository.findById(id);
        if(c.isPresent()){
            return pixRepository.findByContaOrigem(c.get());
        }
        throw new PixException("Conta de origem não encontrada.");
    }

    public List<Pix> listarPorDestino(Long id){
        Optional<Conta> c = contaRepository.findById(id);
        if(c.isPresent()){
            return pixRepository.findByContaDestino(c.get());
        }
        throw new PixException("Conta de origem não encontrada.");
    }

    public Pix listarPorId(Long id){
        Optional<Pix> p = pixRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        throw new PixException("ID não encontrado.");
    }

    public Pix enviarPix(Pix pix) {
        Conta contaOrigem = contaRepository.findById(pix.getContaOrigem().getId())
                .orElseThrow(() -> new PixException("Conta de origem não encontrada."));

        Conta contaDestino = contaRepository.findById(pix.getContaDestino().getId())
                .orElseThrow(() -> new PixException("Conta de destino não encontrada."));

        if (pix.getValor() <= 0) {
            throw new PixException("O valor do Pix deve ser maior que zero.");
        }

        if (contaOrigem.getSaldo() < pix.getValor()) {
            throw new PixException("Saldo insuficiente na conta de origem.");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo() - pix.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + pix.getValor());

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        pix.setDataHora(LocalDateTime.now());
        pix.setStatus(true);
        return pixRepository.save(pix);
    }

    public Pix cancelarPix(Long id){
        Pix p = pixRepository.findById(id)
                .orElseThrow(()-> new PixException("Pix não encontrado."));

        p.getContaOrigem().setSaldo(p.getContaOrigem().getSaldo() + p.getValor());
        p.getContaDestino().setSaldo(p.getContaDestino().getSaldo() - p.getValor());
        p.setStatus(false);

        return pixRepository.save(p);
    }
}
