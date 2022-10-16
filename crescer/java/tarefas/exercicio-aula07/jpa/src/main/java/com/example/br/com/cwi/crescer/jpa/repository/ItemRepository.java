package com.example.br.com.cwi.crescer.jpa.repository;

import com.example.br.com.cwi.crescer.jpa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
