package com.uniesp.backflix.demo.service.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SerieRequest {

    @NotBlank(message = "O campo 'Título' é obrigatório.")
    private String tituloSerie;

    private String sinopse;

    @DecimalMin(value = "0.0", message = "A nota mínima é 0")
    @DecimalMax(value = "10.0", message = "A nota máxima é 10")
    private Double nota;

    @NotBlank(message = "O campo 'Data de Lançamento' é obrigatório.")
    private String dataLancamento;

    private Integer quantidadeTemporadas;

    @NotNull(message = "O campo 'Quantidade de Episódios' é obrigatório.")
    private Integer quantidadeEpisodios;

    @NotNull(message = "O campo 'Gênero ID' é obrigatório.")
    private Long generoId;

    @NotBlank(message = "O campo 'Classificação Indicativa' é obrigatório.")
    private String classificacaoIdade;
}