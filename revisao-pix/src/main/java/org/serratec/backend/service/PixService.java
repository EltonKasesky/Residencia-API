package org.serratec.backend.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.dto.ContaResponseDTO;
import org.serratec.backend.dto.PixRequestDTO;
import org.serratec.backend.dto.PixResponseDTO;
import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Pix;
import org.serratec.backend.exception.PixException;
import org.serratec.backend.repository.ContaRepository;
import org.serratec.backend.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PixService {
    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<PixResponseDTO> listarPix(){
        List<Pix> pixs = pixRepository.findAll();
        List<PixResponseDTO> pixDTOs = new ArrayList<>();

        pixs.forEach(pix -> {
            ContaResponseDTO dtoOrigem = new ContaResponseDTO(
                    pix.getContaOrigem().getId(),
                    pix.getContaOrigem().getNomeTitular(),
                    pix.getContaOrigem().getEmail(),
                    pix.getContaOrigem().getSaldo(),
                    pix.getContaOrigem().getPixEnviados(),
                    pix.getContaOrigem().getPixRecebidos()
            );

            ContaResponseDTO dtoDestino = new ContaResponseDTO(
                    pix.getContaDestino().getId(),
                    pix.getContaDestino().getNomeTitular(),
                    pix.getContaDestino().getEmail(),
                    pix.getContaDestino().getSaldo(),
                    pix.getContaDestino().getPixEnviados(),
                    pix.getContaDestino().getPixRecebidos()
            );

            pixDTOs.add(new PixResponseDTO(
                    pix.getId(),
                    pix.getData(),
                    pix.getValor(),
                    dtoOrigem,
                    dtoDestino
            ));
        });

        return pixDTOs;
    }

    public PixResponseDTO listarPorId(Long id){
        Pix p = pixRepository.findById(id).orElseThrow(()-> new PixException("Pix não encontrado baseado na busca por id."));

        ContaResponseDTO dtoOrigem = new ContaResponseDTO(
                p.getContaOrigem().getId(),
                p.getContaOrigem().getNomeTitular(),
                p.getContaOrigem().getEmail(),
                p.getContaOrigem().getSaldo(),
                p.getContaOrigem().getPixEnviados(),
                p.getContaOrigem().getPixRecebidos()
        );

        ContaResponseDTO dtoDestino = new ContaResponseDTO(
                p.getContaDestino().getId(),
                p.getContaDestino().getNomeTitular(),
                p.getContaDestino().getEmail(),
                p.getContaDestino().getSaldo(),
                p.getContaDestino().getPixEnviados(),
                p.getContaDestino().getPixRecebidos()
        );

        return new PixResponseDTO(
                p.getId(),
                p.getData(),
                p.getValor(),
                dtoOrigem,
                dtoDestino
        );
    }

    @Transactional
    public PixResponseDTO enviarPix(PixRequestDTO dto){
        Conta contaOrigem = contaRepository.findById(dto.getContaOrigem().getId())
                .orElseThrow(()-> new PixException("Pix de conta de origem não encontrado baseado na busca por id."));

        Conta contaDestino = contaRepository.findById(dto.getContaDestino().getId())
                .orElseThrow(()-> new PixException("Pix de conta de destino não encontrado baseado na busca por id."));

        if(!(contaOrigem.getSaldo() >= dto.getValor())){
            throw new PixException("Saldo insuficiente.");
        }

        if(dto.getValor() <= 0){
            throw new PixException("O valor da transação deve ser maior que 0 (zero).");
        }

        if(contaOrigem.getId().equals(dto.getContaDestino().getId())){
            throw new PixException("Não é possivel enviar valores para a conta de origem.");
        }

        Pix pix = new Pix();
        pix.setData(LocalDateTime.now());
        pix.setValor(dto.getValor());
        pix.setContaOrigem(contaOrigem);
        pix.setContaDestino(contaDestino);
        pix.setStatus(true);
        pixRepository.save(pix);

        ContaResponseDTO dtoOrigem = new ContaResponseDTO(
                pix.getContaOrigem().getId(),
                pix.getContaOrigem().getNomeTitular(),
                pix.getContaOrigem().getEmail(),
                pix.getContaOrigem().getSaldo(),
                pix.getContaOrigem().getPixEnviados(),
                pix.getContaOrigem().getPixRecebidos()
        );

        ContaResponseDTO dtoDestino = new ContaResponseDTO(
                pix.getContaDestino().getId(),
                pix.getContaDestino().getNomeTitular(),
                pix.getContaDestino().getEmail(),
                pix.getContaDestino().getSaldo(),
                pix.getContaDestino().getPixEnviados(),
                pix.getContaDestino().getPixRecebidos()
        );

        contaOrigem.setSaldo(contaOrigem.getSaldo() - dto.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + dto.getValor());
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        return new PixResponseDTO(
                pix.getId(),
                pix.getData(),
                pix.getValor(),
                dtoOrigem,
                dtoDestino
        );
    }

    @Transactional
    public PixResponseDTO cancelarPix(Long id){
        Pix pix = pixRepository.findById(id)
                .orElseThrow(()-> new PixException("Pix não encontrado baseado na busca por id."));

        Conta contaOrigem = contaRepository.findById(pix.getContaOrigem().getId())
                .orElseThrow(()-> new PixException("Pix de conta de origem não encontrado baseado na busca por id."));

        Conta contaDestino = contaRepository.findById(pix.getContaDestino().getId())
                .orElseThrow(()-> new PixException("Pix de conta de destino não encontrado baseado na busca por id."));

        pix.setStatus(false);
        pixRepository.save(pix);

        contaOrigem.setSaldo(contaOrigem.getSaldo() + pix.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() - pix.getValor());
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        ContaResponseDTO dtoOrigem = new ContaResponseDTO(
                pix.getContaOrigem().getId(),
                pix.getContaOrigem().getNomeTitular(),
                pix.getContaOrigem().getEmail(),
                pix.getContaOrigem().getSaldo(),
                pix.getContaOrigem().getPixEnviados(),
                pix.getContaOrigem().getPixRecebidos()
        );

        ContaResponseDTO dtoDestino = new ContaResponseDTO(
                pix.getContaDestino().getId(),
                pix.getContaDestino().getNomeTitular(),
                pix.getContaDestino().getEmail(),
                pix.getContaDestino().getSaldo(),
                pix.getContaDestino().getPixEnviados(),
                pix.getContaDestino().getPixRecebidos()
        );

        return new PixResponseDTO(
                pix.getId(),
                pix.getData(),
                pix.getValor(),
                dtoOrigem,
                dtoDestino
        );
    }
}
