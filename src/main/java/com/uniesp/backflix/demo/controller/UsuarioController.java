package com.uniesp.backflix.demo.controller;


import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.service.UsuarioService;
import com.uniesp.backflix.demo.service.dtos.UsuarioRequestDTO;
import com.uniesp.backflix.demo.service.dtos.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuarios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        Page<UsuarioResponseDTO> usuarios = service.listar(pagina, tamanho);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody @Valid UsuarioRequestDTO usuarioDto) {
        log.info("cadastrando novo autor: {}", usuarioDto.nomeCompleto());

        UsuarioResponseDTO usuarioResponseDTO = service.salvar(usuarioDto);

        URI uri = UriUtils.criarUriParaRecurso(usuarioResponseDTO.id());

        return ResponseEntity.created(uri).body(usuarioResponseDTO);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable("cpf") String cpf) {
        UsuarioResponseDTO usuarioResponseDTO = service.buscar(cpf);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody UsuarioRequestDTO usuario) {
        service.atualizar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        log.info("deletando autor de ID: {} ", id);
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
