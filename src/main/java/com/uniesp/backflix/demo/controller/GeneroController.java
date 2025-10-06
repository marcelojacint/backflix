package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genero")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<Genero>> listar() {
        return ResponseEntity.ok(generoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Genero> salvar(@Valid @RequestBody Genero genero) {
        Genero salvo = generoService.salvar(genero);
        URI uri = UriUtils.criarUriParaRecurso_Genero(genero.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @Valid @RequestBody Genero genero) {
        generoService.atualizar(id, genero);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        generoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
