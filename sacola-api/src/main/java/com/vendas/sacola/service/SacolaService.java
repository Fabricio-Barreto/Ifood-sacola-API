package com.vendas.sacola.service;

import com.vendas.sacola.model.Item;
import com.vendas.sacola.model.Sacola;
import com.vendas.sacola.resource.dto.ItemDto;

public interface SacolaService {
    
    Item incluirItemNaSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);


}
