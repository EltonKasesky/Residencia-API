package org.serratec.backend.dto;

import java.time.LocalDateTime;

public record PixResponseDTO(Long id, LocalDateTime data, Double valor, ContaResponseDTO contaOrigem, ContaResponseDTO contaDestino) {
}
