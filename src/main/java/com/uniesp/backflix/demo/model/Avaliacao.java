package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.naming.Name;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "avaliacoes")
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_filme")
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "id_serie")
    private Serie serie;

    @Column(name = "comentario", nullable = false, length = 300)
    private String comentario;


    private LocalDate dataAvaliacao = LocalDate.now();


}
