package com.uniesp.backflix.demo.controller;


import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.service.FilmeService;
import com.uniesp.backflix.demo.service.dtos.FilmeRequest;
import com.uniesp.backflix.demo.service.dtos.FilmeResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Filmes", description = "Operações relacionadas a filmes")
@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listar() {
        return ResponseEntity.ok(filmeService.listarResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscar(@PathVariable String id) {
        return ResponseEntity.ok(filmeService.buscarResponse(id));
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> salvar(@Valid @RequestBody FilmeRequest request) {
        FilmeResponse response = filmeService.salvar(request);
        URI uri = UriUtils.criarUriParaRecurso(response.getId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody FilmeRequest request) {
        filmeService.atualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        filmeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}