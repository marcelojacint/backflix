package com.uniesp.backflix.demo.service.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MinhaListaResponseDTO {
    private Long idMinhaLista;
    private String tituloFilme;
    private String tituloSerie;
    private String nomeUsuario;
}
