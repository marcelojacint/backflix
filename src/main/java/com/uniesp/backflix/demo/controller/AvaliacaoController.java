package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.service.AvaliacaoService;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Avaliações", description = "Operações relacionadas a avaliações")
@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;


    @GetMapping
    public ResponseEntity<List<Avaliacao>> listar() {
        List<Avaliacao> listaAvaliacaoDTO = service.listar();
        return ResponseEntity.ok(listaAvaliacaoDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable String id) {
        Avaliacao avaliacao = service.buscar(id);
        return ResponseEntity.ok(avaliacao);
    }


    @PostMapping
    public ResponseEntity<Avaliacao> salvar(@RequestBody Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = service.salvar(avaliacao);
        return ResponseEntity.ok(novaAvaliacao);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable String id, @RequestBody Avaliacao avaliacao) {
        Avaliacao atualizada = service.atualizar(id, avaliacao);
        return ResponseEntity.ok(atualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
