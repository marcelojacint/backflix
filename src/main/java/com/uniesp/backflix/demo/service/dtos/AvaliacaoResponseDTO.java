package com.uniesp.backflix.demo.service.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AvaliacaoResponseDTO {

    private UUID id;
    private String comentario;
    private Integer nota;
    private UUID usuarioId;
    private UUID filmeId;
    private UUID serieId;
}
