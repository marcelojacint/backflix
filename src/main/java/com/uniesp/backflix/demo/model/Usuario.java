package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false, length = 10)
    private String dataNascimento;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "titularCartao", column = @Column(name = "cartao_titular", length = 100)),
            @AttributeOverride(name = "numeroCartao", column = @Column(name = "cartao_numero", length = 16)),
            @AttributeOverride(name = "validadeCartao", column = @Column(name = "cartao_validade", length = 10)),
            @AttributeOverride(name = "codigoSeguranca", column = @Column(name = "cartao_codigo_seguranca", length = 3))
    })
    private Cartao cartao;

}
