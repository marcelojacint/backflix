package com.uniesp.backflix.demo.service.converter;


import com.uniesp.backflix.demo.model.Usuario;
import com.uniesp.backflix.demo.service.dtos.UsuarioRequestDTO;
import com.uniesp.backflix.demo.service.dtos.UsuarioResponseDTO;

public class UsuarioConverter {

    public static Usuario paraUsuario(UsuarioRequestDTO dto) {
        return Usuario
                .builder()
                .nomeCompleto(dto.nomeCompleto())
                .dataNascimento(dto.dataNascimento())
                .email(dto.email())
                .senha(dto.senha())
                .cpf(dto.cpf())
                .build();
    }

    public static UsuarioResponseDTO paraUsuarioResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO
                .builder()
                .id(usuario.getId())
                .nomeCompleto(usuario.getNomeCompleto())
                .dataNascimento(usuario.getDataNascimento())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .cpf(usuario.getCpf())
                .build();
    }


}
