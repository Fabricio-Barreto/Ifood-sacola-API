package com.vendas.sacola.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.sacola.model.Item;
import com.vendas.sacola.model.Sacola;
import com.vendas.sacola.resource.dto.ItemDto;
import com.vendas.sacola.service.SacolaService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(value="/ifood/api/sacolas")
@RestController
@RequestMapping("/ifood/api/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto){
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long idSacola, @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(idSacola, formaPagamento);
    }

}
