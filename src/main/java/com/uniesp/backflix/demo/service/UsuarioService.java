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
        if (repository.existsByEmail(usuario.getEmail()) || repository.existsByCpf(usuario.getCpf())) {
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
        Usuario usuarioExistente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        Usuario usuario = UsuarioConverter.paraUsuario(usuarioRequestDTO);

        if (usuario.getNomeCompleto() != null) usuarioExistente.setNomeCompleto(usuario.getNomeCompleto());
        if (usuario.getEmail() != null) usuarioExistente.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null) usuarioExistente.setSenha(usuario.getSenha());
        if (usuario.getCpf() != null) usuarioExistente.setCpf(usuario.getCpf());
        if (usuario.getDataNascimento() != null) usuarioExistente.setDataNascimento(usuario.getDataNascimento());
        if (usuario.getCartao() != null) usuarioExistente.setCartao(usuario.getCartao());

        Usuario usuarioSalvo = repository.save(usuarioExistente);

        UsuarioResponseDTO usuarioResponseDTO = UsuarioConverter.paraUsuarioResponseDTO(usuarioSalvo);

        return usuarioResponseDTO;
    }

    public void deletar(String id) {
        repository.findById(UUID.fromString(id)).ifPresentOrElse(repository::delete,
                () -> {
                    throw new EntidadeNaoEncontradaException("Usuário não encontrado");
                });


    }

}
