package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "avaliacoes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_filme")
    private Filme filme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_serie")
    private Serie serie;

    @Column(name = "comentario", nullable = false, length = 300)
    private String comentario;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Builder.Default
    @Column(name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao = LocalDate.now();

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {

        // Definir data se estiver nula (somente no persist)
        if (dataAvaliacao == null) {
            dataAvaliacao = LocalDate.now();
        }

        // Validação: tem que ter filme OU série
        if (filme == null && serie == null) {
            throw new IllegalStateException("Pelo menos um filme ou série deve ser informado");
        }
        if (filme != null && serie != null) {
            throw new IllegalStateException("Avaliação deve estar associada a um filme OU uma série, não ambos");
        }
    }

}