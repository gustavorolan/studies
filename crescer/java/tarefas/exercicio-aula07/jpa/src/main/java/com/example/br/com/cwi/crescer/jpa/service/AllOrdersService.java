package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.response.OrderResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.OrderMapper;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllOrdersService {
    @Autowired
    private OrderFromUserRepository orderFromUserRepository;
    
    @Autowired
    private OrderMapper orderMapper;
    
    public List<OrderResponse> get() {
       return orderFromUserRepository.findAll().stream()
                .map(order->orderMapper.toResponse(order))
                .collect(Collectors.toList());
    }
}
