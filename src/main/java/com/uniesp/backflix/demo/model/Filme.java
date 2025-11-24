package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "filmes")
public class Filme {

    @Id //identificando o id da tabela no banco.
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "sinopse", length = 200)
    private String sinopse;

    @Column(name = "nota", length = 10)
    @Min(1)
    @Max(10)
    private Integer nota;

    @Column(name = "data_lancamento")
    private String dataLancamento;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    @Column(name = "classificacao_indicativa", length = 10)
    private String classificacaoIndicativa;
}
