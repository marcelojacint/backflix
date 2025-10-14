package com.uniesp.backflix.demo.controller;


import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.service.UsuarioService;
import com.uniesp.backflix.demo.service.dtos.UsuarioRequestDTO;
import com.uniesp.backflix.demo.service.dtos.UsuarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> listaUsuariosDTO = service.listar();
        return ResponseEntity.ok(listaUsuariosDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody UsuarioRequestDTO usuarioDto) {

        UsuarioResponseDTO usuarioResponseDTO = service.salvar(usuarioDto);

        URI uri = UriUtils.criarUriParaRecurso(usuarioResponseDTO.id());

        return ResponseEntity.created(uri).body(usuarioResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable("id") String id) {
        UsuarioResponseDTO usuarioResponseDTO = service.buscar(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody UsuarioRequestDTO usuario) {
        service.atualizar(id, usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
