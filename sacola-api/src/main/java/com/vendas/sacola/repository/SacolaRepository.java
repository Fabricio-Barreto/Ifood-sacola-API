package com.vendas.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendas.sacola.model.Sacola;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
    
}
