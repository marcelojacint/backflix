package com.uniesp.backflix.demo.service.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UsuarioResponseDTO(UUID id, String nomeCompleto, String dataNascimento, String email, String senha, String cpf) {
}
