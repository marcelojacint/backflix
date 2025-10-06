package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filmes")
public class Filme {

    @Id //identificando o id da tabela no banco.
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @NotBlank(message = "campo obrigatório!")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "sinpse", length = 200)
    private String sinopse;

    @NotNull(message = "campo obrigatório!")
    @Column(name = "nota", length = 10)
    @Size(min = 1, max = 10)
    private Integer nota;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "data_lancamento")
    private String dataLancamento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @NotNull(message = "campo obrigatório!")
    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "classificacao_indicativa", length = 10)
    private String classificacaoIndicativa;
}
