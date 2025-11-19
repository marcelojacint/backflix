package com.uniesp.backflix.demo.controller;


import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.service.GeneroService;
import com.uniesp.backflix.demo.service.dtos.GeneroRequest;
import com.uniesp.backflix.demo.service.dtos.GeneroResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroResponse>> listar() {
        return ResponseEntity.ok(generoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<GeneroResponse> salvar(@Valid @RequestBody GeneroRequest request) {
        GeneroResponse salvo = generoService.salvar(request);
        URI uri = UriUtils.criarUriParaRecurso_Genero(salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponse> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody GeneroRequest request) {
        return ResponseEntity.ok(generoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        generoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
