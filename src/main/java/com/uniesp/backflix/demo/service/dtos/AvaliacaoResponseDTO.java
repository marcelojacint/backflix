package com.uniesp.backflix.demo.service.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record AvaliacaoResponseDTO(
        UUID id,
        UUID usuarioId,
        UUID filmeId,        // Pode ser nulo se for avaliação de série
        UUID serieId,        // Pode ser nulo se for avaliação de filme
        String comentario,
        LocalDate dataAvaliacao
) {
    // Validação implícita do record
    public AvaliacaoResponseDTO {
        if (filmeId == null && serieId == null) {
            throw new IllegalArgumentException("Pelo menos um ID de filme ou série deve ser fornecido");
        }
        if (filmeId != null && serieId != null) {
            throw new IllegalArgumentException("Não é possível ter ID de filme e série ao mesmo tempo");
        }
    }
}