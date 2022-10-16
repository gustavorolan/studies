package com.example.br.com.cwi.crescer.jpa.repository;

import com.example.br.com.cwi.crescer.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
