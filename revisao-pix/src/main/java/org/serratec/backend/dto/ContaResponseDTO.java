package org.serratec.backend.dto;

import org.serratec.backend.entity.Pix;

import java.util.List;

public record ContaResponseDTO(Long id, String nomeTitular, String email, Double saldo, List<Pix> pixEnviados, List<Pix> pixRecebidos) {
}
