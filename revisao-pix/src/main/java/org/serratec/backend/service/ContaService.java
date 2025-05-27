package org.serratec.backend.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.dto.ContaRequestDTO;
import org.serratec.backend.dto.ContaResponseDTO;
import org.serratec.backend.entity.Conta;
import org.serratec.backend.exception.ContaException;
import org.serratec.backend.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ContaResponseDTO> listarContas(){
        List<Conta> contasEntity = contaRepository.findAll();
        List<ContaResponseDTO> contasDTO = new ArrayList<>();

        contasEntity.forEach(c -> {
            contasDTO.add(new ContaResponseDTO(
                    c.getId(),
                    c.getNomeTitular(),
                    c.getEmail(),
                    c.getSaldo(),
                    c.getPixEnviados(),
                    c.getPixRecebidos()));
        });

        return contasDTO;
    }

    public ContaResponseDTO listarPorId(Long id){
        Conta c = contaRepository.findById(id).orElseThrow(()-> new ContaException("Conta não encontrada baseada na busca por id."));

        return new ContaResponseDTO(
                c.getId(),
                c.getNomeTitular(),
                c.getEmail(),
                c.getSaldo(),
                c.getPixEnviados(),
                c.getPixRecebidos()
        );
    }

    @Transactional
    public ContaResponseDTO adicionarConta(ContaRequestDTO dto){
        contaRepository.findByEmail(dto.getEmail()).ifPresent(c -> {
            throw new ContaException("O email " + dto.getEmail() + " já está vinculado a uma conta!");
        });

        if(dto.getSaldo() <= 0){
            throw new ContaException("O saldo para a criação da conta precisa ser maior que 0 (zero)!");
        }

        Conta conta = new Conta();
        conta.setNomeTitular(dto.getNomeTitular());
        conta.setEmail(dto.getEmail());
        conta.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
        conta.setSaldo(dto.getSaldo());
        contaRepository.save(conta);

        return new ContaResponseDTO(
                conta.getId(),
                conta.getNomeTitular(),
                conta.getEmail(),
                conta.getSaldo(),
                conta.getPixEnviados(),
                conta.getPixRecebidos()
        );
    }

    @Transactional
    public List<ContaResponseDTO> adicionarListaContas(List<ContaRequestDTO> dtos){
        List<Conta> contasEntity = new ArrayList<>();
        List<ContaResponseDTO> contasDTO = new ArrayList<>();

        dtos.forEach(dto-> {
           if(contaRepository.findByEmail(dto.getEmail()).isPresent()){
               throw new ContaException("O email " + dto.getEmail() + " já está vinculado a uma conta!");
           }

           if(dto.getSaldo() <= 0){
               throw new ContaException("O saldo para a criação da conta precisa ser maior que 0 (zero)!");
           }

           Conta conta = new Conta();
           conta.setNomeTitular(dto.getNomeTitular());
           conta.setEmail(dto.getEmail());
           conta.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
           conta.setSaldo(dto.getSaldo());
           contasEntity.add(conta);
        });

        contaRepository.saveAll(contasEntity);

        contasEntity.forEach(c-> {
            contasDTO.add(new ContaResponseDTO(
                    c.getId(),
                    c.getNomeTitular(),
                    c.getEmail(),
                    c.getSaldo(),
                    c.getPixEnviados(),
                    c.getPixRecebidos()
            ));
        });

        return contasDTO;
    }

    @Transactional
    public ContaResponseDTO atualizarConta(Long id, ContaRequestDTO dto){
        Conta conta = contaRepository.findById(id).orElseThrow(()-> new ContaException("Conta não encontrada baseada na busca por id."));

        if(dto.getSaldo() <= 0){
            throw new ContaException("O saldo para a edição da conta precisa ser maior que 0 (zero)!");
        }

        conta.setId(id);
        conta.setEmail(dto.getEmail());
        conta.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
        conta.setSaldo(dto.getSaldo());
        contaRepository.save(conta);

        return new ContaResponseDTO(
                conta.getId(),
                conta.getNomeTitular(),
                conta.getEmail(),
                conta.getSaldo(),
                conta.getPixEnviados(),
                conta.getPixRecebidos()
        );
    }

    @Transactional
    public void deletarConta(Long id){
        contaRepository.findById(id).orElseThrow(()-> new ContaException("Conta não encontrada baseada na busca por id."));
        contaRepository.deleteById(id);
    }
}
