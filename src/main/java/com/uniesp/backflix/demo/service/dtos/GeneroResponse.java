package com.uniesp.backflix.demo.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneroResponse {
    private Long id;
    private String genero;
}