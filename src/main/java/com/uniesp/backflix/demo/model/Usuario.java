package com.uniesp.backflix.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "campo obrigatório!")
    @Column(name = "data_nascimento", nullable = false, length = 10)
    private String dataNascimento;

    @NotBlank(message = "campo obrigatório!")
    @Email(message = "insira email válido!")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Size(min = 6)
    @NotNull(message = "campo obrigatório!")
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @NotNull(message = "campo obrigatório!")
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
