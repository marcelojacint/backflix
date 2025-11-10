package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.service.GeneroService;
import com.uniesp.backflix.demo.service.dtos.GeneroRequest;
import com.uniesp.backflix.demo.service.dtos.GeneroResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroResponse>> listar() {
        List<GeneroResponse> generos = generoService.listar()
                .stream()
                .map(g -> new GeneroResponse(g.getId(), g.getGenero()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponse> buscarPorId(@PathVariable Long id) {
        Genero genero = generoService.buscarPorId(id);
        return ResponseEntity.ok(new GeneroResponse(genero.getId(), genero.getGenero()));
    }

    @PostMapping
    public ResponseEntity<GeneroResponse> salvar(@Valid @RequestBody GeneroRequest request) {
        Genero genero = new Genero();
        genero.setGenero(request.getGenero());

        Genero salvo = generoService.salvar(genero);
        URI uri = UriUtils.criarUriParaRecurso_Genero(salvo.getId());
        return ResponseEntity.created(uri).body(new GeneroResponse(salvo.getId(), salvo.getGenero()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody GeneroRequest request) {
        Genero genero = new Genero(id, request.getGenero());
        generoService.atualizar(id, genero);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        generoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
