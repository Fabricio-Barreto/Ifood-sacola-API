package com.vendas.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendas.sacola.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
