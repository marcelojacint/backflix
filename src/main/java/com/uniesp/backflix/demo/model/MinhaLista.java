package com.uniesp.backflix.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_minha_lista")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MinhaLista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_minha_lista")
    private Long idMinhaLista;

    @ManyToOne
    @JoinColumn(name = "id_filme", nullable = true)
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "id_serie", nullable = true)
    private Serie serie;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
