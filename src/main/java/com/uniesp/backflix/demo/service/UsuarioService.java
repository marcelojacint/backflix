package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Usuario;
import com.uniesp.backflix.demo.repository.UsuarioRepository;
import com.uniesp.backflix.demo.service.converter.UsuarioConverter;
import com.uniesp.backflix.demo.service.dtos.UsuarioRequestDTO;
import com.uniesp.backflix.demo.service.dtos.UsuarioResponseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Data
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Page<UsuarioResponseDTO> listar(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Usuario> usuariosPage = repository.findAll(pageable);

        if (usuariosPage.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum usuário encontrado!");
        }

        return usuariosPage.map(UsuarioConverter::paraUsuarioResponseDTO);
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioDto) {
        Usuario usuario = UsuarioConverter.paraUsuario(usuarioDto);
        validaEmailECpfUsuario(usuario);
        repository.save(usuario);
        return UsuarioConverter.paraUsuarioResponseDTO(usuario);

    }

    private void validaEmailECpfUsuario(Usuario usuario) {
        if (!repository.existsByEmailAndCpf(usuario.getEmail(), usuario.getCpf())) {
            throw new EntidadeNaoEncontradaException("usuário já existe no banco!");
        }
    }

    public UsuarioResponseDTO buscar(String cpf) {

        Usuario usuario = verificaUsuarioPorcpf(cpf);
        UsuarioResponseDTO usuarioResponseDTO = UsuarioConverter.paraUsuarioResponseDTO(usuario);
        return usuarioResponseDTO;
    }

    private Usuario verificaUsuarioPorcpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("usuário não encontrado!"));
    }

    public UsuarioResponseDTO atualizar(String id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario existente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        Usuario usuario = UsuarioConverter.paraUsuario(usuarioRequestDTO);

        if (usuario.getNomeCompleto() != null) existente.setNomeCompleto(usuario.getNomeCompleto());
        if (usuario.getEmail() != null) existente.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null) existente.setSenha(usuario.getSenha());
        if (usuario.getCpf() != null) existente.setCpf(usuario.getCpf());
        if (usuario.getDataNascimento() != null) existente.setDataNascimento(usuario.getDataNascimento());
        if (usuario.getCartao() != null) existente.setCartao(usuario.getCartao());

        Usuario usuarioSalvo = repository.save(existente);

        UsuarioResponseDTO usuarioResponseDTO = UsuarioConverter.paraUsuarioResponseDTO(usuarioSalvo);

        return usuarioResponseDTO;
    }

    public void deletar(String id) {
        repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("usuário não encontrado!"));

    }

}
