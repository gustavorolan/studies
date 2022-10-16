package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.response.OrderResponse;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.model.Situation;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import com.example.br.com.cwi.crescer.jpa.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCloseService {
    @Autowired
    private  OrderService orderService;
    @Autowired
    private Validators validators;
    @Autowired
    private OrderFromUserRepository orderFromUserRepository;
    @Autowired
    private OrderFromUserFinderService orderFromUserFinderService;

    public OrderResponse close(Long id) {
        OrderFromUser order = orderFromUserFinderService.findByIdWithException(id);
        validators.ifSomethingIsNotFilledThrowsAnException(order);
        order.setSituation(Situation.CLOSED);
        orderFromUserRepository.save(order);
        return orderService.get(id);
    }
}
