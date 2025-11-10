package com.uniesp.backflix.demo.service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AvaliacaoRequestDTO(
        @NotNull(message = "ID do usuário é obrigatório!")
        UUID usuarioId,
        
        UUID filmeId,
        
        UUID serieId,
        
        @NotBlank(message = "Comentário é obrigatório!")
        String comentario
) {}
