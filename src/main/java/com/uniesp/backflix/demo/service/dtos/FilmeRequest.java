package com.uniesp.backflix.demo.service.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilmeRequest {

    @NotBlank(message = "campo obrigatório!")
    private String nome;

    @NotBlank(message = "O campo 'Título' é obrigatório.")
    private String titulo;

    @NotBlank(message = "O campo 'Sinopse' é obrigatório.")
    private String sinopse;

    @NotNull(message = "O campo 'Nota' é obrigatório.")
    @Min(1) @Max(10)
    private Integer nota;

    @NotBlank(message = "O campo 'Data de Lançamento' é obrigatório.")
    private String dataLancamento;

    @NotNull(message = "O campo 'Gênero ID' é obrigatório.")
    private Long generoId;

    @NotNull(message = "O campo 'Duração' é obrigatório.")
    private Integer duracaoMinutos;

    @NotBlank(message = "O campo 'Classificação Indicativa' é obrigatório.")
    private String classificacaoIndicativa;
}