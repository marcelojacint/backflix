package com.uniesp.backflix.demo.service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeneroRequest {
    @NotBlank(message = "O campo 'genero' é obrigatório.")
    private String genero;
}