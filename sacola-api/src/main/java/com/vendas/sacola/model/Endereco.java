package com.vendas.sacola.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Endereco {
    private String cep;
    
    private String complemento;
}
