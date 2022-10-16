package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.response.OrderResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.OrderMapper;

import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;

import com.example.br.com.cwi.crescer.jpa.repository.ItemRepository;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    private OrderFromUserRepository orderFromUserRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderFromUserFinderService orderFromUserFinderService;

    public OrderResponse get(Long id) {
        OrderFromUser order = orderFromUserFinderService.findByIdWithException(id);

        return  orderMapper.toResponse(order);
    }
}
