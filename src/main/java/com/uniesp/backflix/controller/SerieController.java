package com.uniesp.backflix.controller;

import com.uniesp.backflix.model.Serie;
import com.uniesp.backflix.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/serie")
@RequiredArgsConstructor
public class SerieController {

    private final SerieService service;

    @GetMapping
    public ResponseEntity<List<Serie>> listar() {
        List<Serie> series = service.listar();
        return ResponseEntity.ok(series);
    }

    @PostMapping
    public ResponseEntity<Serie> salvar(@RequestBody Serie serie) {
        Serie serieSalva = service.salvar(serie);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serieSalva.getId())
                .toUri();
        return ResponseEntity.created(uri).body(serieSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> buscar(@PathVariable("id") String id) {
        Serie serie = service.buscar(id);
        return ResponseEntity.ok(serie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody Serie serie) {
        service.atualizar(id, serie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
