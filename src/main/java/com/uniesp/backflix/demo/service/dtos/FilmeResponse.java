package com.uniesp.backflix.demo.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeResponse {
    private UUID id;
    private String titulo;
    private String sinopse;
    private Integer nota;
    private String dataLancamento;
    private Integer duracaoMinutos;
    private String classificacaoIndicativa;
    private GeneroDTO genero;
}