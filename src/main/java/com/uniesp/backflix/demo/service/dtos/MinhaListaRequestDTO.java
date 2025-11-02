package com.uniesp.backflix.demo.service.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MinhaListaRequestDTO {
    private UUID idFilme;
    private UUID idSerie;
    private UUID idUsuario;
}
