package com.uniesp.backflix.demo.service.converter;

import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.model.Usuario;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoRequestDTO;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoResponseDTO;

public class AvaliacaoConverter {

    public static Avaliacao paraAvaliacao(AvaliacaoRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO não pode ser nulo");
        }

        Avaliacao.AvaliacaoBuilder builder = Avaliacao.builder()
                .usuario(Usuario.builder().id(dto.usuarioId()).build())
                .comentario(dto.comentario());

        if (dto.filmeId() != null) {
            builder.filme(Filme.builder().id(dto.filmeId()).build());
        }
        if (dto.serieId() != null) {
            builder.serie(Serie.builder().id(dto.serieId()).build());
        }

        return builder.build();
    }

    public static AvaliacaoResponseDTO paraAvaliacaoResponseDTO(Avaliacao avaliacao) {
        if (avaliacao == null) {
            return null;
        }

        return AvaliacaoResponseDTO.builder()
                .id(avaliacao.getId())
                .usuarioId(avaliacao.getUsuario().getId())
                .filmeId(avaliacao.getFilme() != null ? avaliacao.getFilme().getId() : null)
                .serieId(avaliacao.getSerie() != null ? avaliacao.getSerie().getId() : null)
                .comentario(avaliacao.getComentario())
                .dataAvaliacao(avaliacao.getDataAvaliacao())
                .build();
    }
}