package com.vendas.sacola.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vendas.sacola.enumeration.FormaPagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data // traz todos os set e get
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

    private Double valorTotal;

    @Enumerated
    private FormaPagamento formaPagamento;

    private boolean fechada;

}
