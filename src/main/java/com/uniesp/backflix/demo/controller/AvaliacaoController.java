package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.service.AvaliacaoService;
import com.uniesp.backflix.demo.service.converter.AvaliacaoConverter;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoRequestDTO;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Endpoints para gerenciamento de avaliações")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listar() {
        List<Avaliacao> lista = service.listar();
        return ResponseEntity.ok(lista.stream().map(AvaliacaoConverter::toResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> buscar(@PathVariable String id) {
        Avaliacao avaliacao = service.buscar(id);
        return ResponseEntity.ok(AvaliacaoConverter.toResponseDTO(avaliacao));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> salvar(@RequestBody AvaliacaoRequestDTO dto) {
        Avaliacao nova = service.salvar(dto);
        return ResponseEntity.ok(AvaliacaoConverter.toResponseDTO(nova));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable String id, @RequestBody AvaliacaoRequestDTO dto) {
        Avaliacao atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(AvaliacaoConverter.toResponseDTO(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
