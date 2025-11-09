package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "series")
@Data
@Builder
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "titulo_serie", nullable = false, length = 100)
    private String tituloSerie;

    @Size(max = 1000)
    @Column(name = "sinopse", length = 1000)
    private String sinopse;

    @DecimalMin(value = "0.0", message = "a nota mínima é 0")
    @DecimalMax(value = "10.0", message = "a nota máxima é 10")
    @Column(name = "nota")
    private Double nota;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "data_lancamento", nullable = false, length = 10)
    private String dataLancamento;

    @Column(name = "quantidade_temporadas")
    private Integer quantidadeTemporadas;

    @NotNull(message = "campo obrigatório!")
    @Column(name = "quantidade_episodios", nullable = false)
    private Integer quantidadeEpisodios;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "classificacao_idade", nullable = false, length = 20)
    private String classificacaoIdade;

}
