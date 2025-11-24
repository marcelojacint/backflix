package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.service.GeneroService;
import com.uniesp.backflix.demo.service.SerieService;
import com.uniesp.backflix.demo.service.dtos.GeneroDTO;
import com.uniesp.backflix.demo.service.dtos.GeneroResponse;
import com.uniesp.backflix.demo.service.dtos.SerieRequest;
import com.uniesp.backflix.demo.service.dtos.SerieResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Séries", description = "Operações relacionadas a séries")
@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
@Builder
public class SerieController {

    private final SerieService service;
    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<SerieResponse>> listar() {
        List<SerieResponse> series = service.listar().stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponse> buscar(@PathVariable String id) {
        Serie serie = service.buscar(id);
        return ResponseEntity.ok(service.toResponse(serie));
    }

    @PostMapping
    public ResponseEntity<SerieResponse> salvar(@Valid @RequestBody SerieRequest request) {
        Genero genero = generoService.buscarEntidadePorId(request.getGeneroId());
        Serie serie = service.toEntity(request, genero);
        Serie salva = service.salvar(serie);

        URI uri = UriUtils.criarUriParaRecurso(salva.getId());
        return ResponseEntity.created(uri).body(service.toResponse(salva));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody SerieRequest request) {
        Genero genero = generoService.buscarEntidadePorId(request.getGeneroId());
        Serie serie = service.toEntity(request, genero);

        service.atualizar(id, serie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}