package com.uniesp.backflix.demo.controller;


import com.uniesp.backflix.demo.service.MinhaListaService;
import com.uniesp.backflix.demo.service.dtos.MinhaListaRequestDTO;
import com.uniesp.backflix.demo.service.dtos.MinhaListaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minha-lista")
@RequiredArgsConstructor
public class MinhaListaController {

    private final MinhaListaService minhaListaService;

    @PostMapping
    public ResponseEntity<MinhaListaResponseDTO> adicionar(@RequestBody MinhaListaRequestDTO dto) {
        return ResponseEntity.ok(minhaListaService.adicionar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MinhaListaResponseDTO>> listar() {
        return ResponseEntity.ok(minhaListaService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        minhaListaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
