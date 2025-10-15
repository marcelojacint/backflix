package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.service.FilmeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/filme")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> listaFilmes = filmeService.listar();
        return ResponseEntity.ok(listaFilmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));

    }

    @PostMapping
    public ResponseEntity<Filme> salvar(@Valid @RequestBody Filme filme) {
        Filme filmeSalvo = filmeService.salvar(filme);
        URI uri = UriUtils.criarUriParaRecurso(filme.getId());
        return ResponseEntity.created(uri).body(filmeSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.atualizar(id, filme);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        filmeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}