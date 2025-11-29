package com.uniesp.backflix.demo.service.converter;

import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoResponseDTO;

public class AvaliacaoConverter {

    public static AvaliacaoResponseDTO toResponseDTO(Avaliacao a) {
        return AvaliacaoResponseDTO.builder()
                .id(a.getId())
                .comentario(a.getComentario())
                .nota(a.getNota())
                .usuarioId(a.getUsuario().getId())
                .filmeId(a.getFilme() != null ? a.getFilme().getId() : null)
                .serieId(a.getSerie() != null ? a.getSerie().getId() : null)
                .build();
    }
}
