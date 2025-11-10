package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.service.GeneroService;
import com.uniesp.backflix.demo.service.SerieService;
import com.uniesp.backflix.demo.service.dtos.GeneroDTO;
import com.uniesp.backflix.demo.service.dtos.SerieRequest;
import com.uniesp.backflix.demo.service.dtos.SerieResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
                .map(s -> new SerieResponse(
                        s.getId(),
                        s.getTituloSerie(),
                        s.getSinopse(),
                        s.getNota(),
                        s.getDataLancamento(),
                        s.getQuantidadeTemporadas(),
                        s.getQuantidadeEpisodios(),
                        s.getClassificacaoIdade(),
                        new GeneroDTO(s.getGenero().getId(), s.getGenero().getGenero())
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponse> buscar(@PathVariable String id) {
        Serie s = service.buscar(id);
        SerieResponse response = new SerieResponse(
                s.getId(),
                s.getTituloSerie(),
                s.getSinopse(),
                s.getNota(),
                s.getDataLancamento(),
                s.getQuantidadeTemporadas(),
                s.getQuantidadeEpisodios(),
                s.getClassificacaoIdade(),
                new GeneroDTO(s.getGenero().getId(), s.getGenero().getGenero())
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SerieResponse> salvar(@Valid @RequestBody SerieRequest request) {
        Genero genero = generoService.buscarPorId(request.getGeneroId());

        Serie serie = Serie.builder()
                .tituloSerie(request.getTituloSerie())
                .sinopse(request.getSinopse())
                .nota(request.getNota())
                .dataLancamento(request.getDataLancamento())
                .quantidadeTemporadas(request.getQuantidadeTemporadas())
                .quantidadeEpisodios(request.getQuantidadeEpisodios())
                .classificacaoIdade(request.getClassificacaoIdade())
                .genero(genero)
                .build();

        Serie salva = service.salvar(serie);

        URI uri = UriUtils.criarUriParaRecurso(salva.getId());
        SerieResponse response = new SerieResponse(
                salva.getId(),
                salva.getTituloSerie(),
                salva.getSinopse(),
                salva.getNota(),
                salva.getDataLancamento(),
                salva.getQuantidadeTemporadas(),
                salva.getQuantidadeEpisodios(),
                salva.getClassificacaoIdade(),
                new GeneroDTO(genero.getId(), genero.getGenero())
        );

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody SerieRequest request) {
        Genero genero = generoService.buscarPorId(request.getGeneroId());

        Serie serie = Serie.builder()
                .tituloSerie(request.getTituloSerie())
                .sinopse(request.getSinopse())
                .nota(request.getNota())
                .dataLancamento(request.getDataLancamento())
                .quantidadeTemporadas(request.getQuantidadeTemporadas())
                .quantidadeEpisodios(request.getQuantidadeEpisodios())
                .classificacaoIdade(request.getClassificacaoIdade())
                .genero(genero)
                .build();


        service.atualizar(id, serie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}