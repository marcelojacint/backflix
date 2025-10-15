package com.uniesp.backflix.demo.service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioRequestDTO(
        @NotBlank(message = "campo obrigatório!")
        String nomeCompleto,
        @NotBlank(message = "campo obrigatório!")
        String dataNascimento,
        @NotBlank(message = "campo obrigatório!")
        @Email(message = "insira email válido!")
        String email,
        @Size(min = 6)
        @NotNull(message = "campo obrigatório!")
        String senha,
        @NotNull(message = "campo obrigatório!")
        String cpf) {
}
