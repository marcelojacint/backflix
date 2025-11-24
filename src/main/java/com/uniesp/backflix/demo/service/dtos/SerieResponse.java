package com.uniesp.backflix.demo.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieResponse {
    private UUID id;
    private String titulo;
    private String sinopse;
    private Double nota;
    private String dataLancamento;
    private Integer quantidadeTemporadas;
    private Integer quantidadeEpisodios;
    private String classificacaoIdade;
    private GeneroDTO genero;
}