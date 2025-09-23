package com.uniesp.backflix.service;

import com.uniesp.backflix.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.model.Cartao;
import com.uniesp.backflix.model.Usuario;
import com.uniesp.backflix.repository.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Data
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        //validacao de usuario com email ou cpf iguais no banco
        return repository.save(usuario);
    }

    public Usuario buscar(String id) {

        return repository.findById(UUID.fromString(id)).orElseThrow(() -> new EntidadeNaoEncontradaException("usuário não encontrado!"));
    }

    public Usuario atualizar(String id, Usuario usuario) {
        Usuario existente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        if (usuario.getNomeCompleto() != null) existente.setNomeCompleto(usuario.getNomeCompleto());
        if (usuario.getEmail() != null) existente.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null) existente.setSenha(usuario.getSenha());
        if (usuario.getCpf() != null) existente.setCpf(usuario.getCpf());
        if (usuario.getDataNascimento() != null) existente.setDataNascimento(usuario.getDataNascimento());
        if (usuario.getCartao() != null) existente.setCartao(usuario.getCartao());

        return repository.save(existente);
    }

    public void deletar(String id) {
        repository.findById(UUID.fromString(id))
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new EntidadeNaoEncontradaException("Usuário não encontrado!");
                        }
                );
    }

    public void atualizarCartao(String id, Cartao cartao) {
        UUID uuid = UUID.fromString(id);
        Usuario usuario = repository.findById(uuid)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        usuario.setCartao(cartao);
        repository.save(usuario);
    }

}
