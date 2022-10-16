package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.request.AddNewOrderRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.AddNewOrderResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.AddNewOrderServiceMapper;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.model.Situation;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddNewOrderService {
    @Autowired
    private AddNewOrderServiceMapper addNewOrderServiceMapper;

    @Autowired
    private OrderFromUserRepository orderFromUserRepository;


    public AddNewOrderResponse add(AddNewOrderRequest request) {
        OrderFromUser orderFromUser = addNewOrderServiceMapper.toEntity(request);
        orderFromUser.setSituation(Situation.OPEN);
        orderFromUserRepository.save(orderFromUser);
        return addNewOrderServiceMapper.toResponse(orderFromUser);
    }
}
