package com.uniesp.backflix.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.repository.FilmeRepository;
import com.uniesp.backflix.demo.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String MODEL = "gemini-2.0-flash";
    private static final String BASE_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/" + MODEL + ":generateContent?key=";

    private final RestTemplate restTemplate = new RestTemplate();
    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;



    public String gerarResumo(String nome) {
        String prompt = String.format("""
                Gere uma lista dos principais atores sobre o filme ou série chamado "%s".
                Responda apenas com o texto da listae seus papeis, sem formatação JSON.
                """, nome);

        try {
            existeFilmeOuSeriePorNome(nome);
            return chamarGemini(prompt);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException("Erro ao listar: " + e.getMessage());
        }
    }

    private void existeFilmeOuSeriePorNome(String nome) {
        if(!filmeRepository.existsByNome(nome) || !serieRepository.existsByNome(nome)) {
            throw new EntidadeNaoEncontradaException("Filme ou Série não encontrados!");
        }
    }


    private String chamarGemini(String prompt) {
        String url = BASE_URL + apiKey;

        Map<String, Object> requestBody = Map.of(
                "contents", java.util.List.of(
                        Map.of("parts", java.util.List.of(Map.of("text", prompt)))
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response =
                    restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getBody() == null) {
                throw new IllegalStateException("Resposta vazia da API Gemini");
            }

            var candidates = (java.util.List<Map<String, Object>>) response.getBody().get("candidates");
            var content = (Map<String, Object>) candidates.get(0).get("content");
            var parts = (java.util.List<Map<String, Object>>) content.get("parts");

            return parts.get(0).get("text").toString();

        } catch (RestClientException e) {
            throw new RuntimeException("Erro ao se conectar à API Gemini: " + e.getMessage(), e);
        }
    }
}



