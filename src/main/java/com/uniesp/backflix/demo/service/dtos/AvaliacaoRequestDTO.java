package com.uniesp.backflix.demo.service.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class AvaliacaoRequestDTO {

        private UUID usuarioId;
        private UUID filmeId;
        private UUID serieId;
        private String comentario;
        private Integer nota;
}
