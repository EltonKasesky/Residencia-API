package org.serratec.backend.repository;

import org.serratec.backend.entity.Conta;
import org.serratec.backend.entity.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {
    List<Pix> findByContaOrigem(Conta contaOrigem);
    List<Pix> findByContaDestino(Conta contaDestino);
}
