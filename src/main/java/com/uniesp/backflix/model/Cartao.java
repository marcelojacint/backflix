package com.uniesp.backflix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
public class Cartao {

    private String titularCartao;
    private String numeroCartao;
    private String validadeCartao;
    private String codigoSeguranca;


}
