package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/autores")
    public ResponseEntity<String> gerarResumoESimilares(@RequestParam String nome) {
        String autores = geminiService.gerarResumo(nome);
        return ResponseEntity.ok(autores);
    }
}
