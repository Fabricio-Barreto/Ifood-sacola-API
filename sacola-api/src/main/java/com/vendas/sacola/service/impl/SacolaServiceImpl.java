package com.vendas.sacola.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vendas.sacola.enumeration.FormaPagamento;
import com.vendas.sacola.model.Item;
import com.vendas.sacola.model.Restaurante;
import com.vendas.sacola.model.Sacola;
import com.vendas.sacola.repository.ItemRepository;
import com.vendas.sacola.repository.ProdutoRepository;
import com.vendas.sacola.repository.SacolaRepository;
import com.vendas.sacola.resource.dto.ItemDto;
import com.vendas.sacola.service.SacolaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()){
            throw new RuntimeException("inclua itens na sacola!");
        }

        FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        sacolaRepository.save(sacola);

        return sacolaRepository.save(sacola);   
    }

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getIdSacola());
        
        if(sacola.isFechada()) {
            throw new RuntimeException("Essa sacola está fechada!");
        }

        Item itemParaSerInserido = Item.builder()
            .quantidade(itemDto.getQuantidade())
            .sacola(sacola)
            .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(() -> {
                throw new RuntimeException("Essa produto não existe!");
            }))
            .build();

        List<Item> itensDaSacola = sacola.getItens();
        if(itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

            if(restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes.");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();

        //Tentar alterar esse for utilizando o método stream.

        for(Item itemDaSacola: itensDaSacola) {
            double valorTotalitem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();

            valorDosItens.add(valorTotalitem);
        }

        Double valorTotalSacola = valorDosItens.stream().mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem).sum();

        sacola.setValorTotal(valorTotalSacola);
        
        sacolaRepository.save(sacola);

        return itemRepository.save(itemParaSerInserido);
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
            () -> {
                throw new RuntimeException("Essa sacola não existe!");
            }
        );
    }
    
}
