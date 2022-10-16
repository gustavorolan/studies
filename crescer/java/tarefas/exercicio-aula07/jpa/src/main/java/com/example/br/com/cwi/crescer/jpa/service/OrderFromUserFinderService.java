package com.example.br.com.cwi.crescer.jpa.service;


import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderFromUserFinderService {

    @Autowired
    private OrderFromUserRepository orderFromUserRepository;

    public OrderFromUser findByIdWithException(Long id) {
        return orderFromUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "pedido nao existe"));
    }

}
