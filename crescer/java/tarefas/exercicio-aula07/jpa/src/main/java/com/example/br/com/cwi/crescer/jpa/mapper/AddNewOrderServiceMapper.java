package com.example.br.com.cwi.crescer.jpa.mapper;

import com.example.br.com.cwi.crescer.jpa.controller.request.AddNewOrderRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.AddNewOrderResponse;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddNewOrderServiceMapper {
    public AddNewOrderResponse toResponse(OrderFromUser entity){
        return new ModelMapper().map(entity,AddNewOrderResponse.class);
    }
    public OrderFromUser  toEntity(AddNewOrderRequest request){
       return  new ModelMapper().map(request,OrderFromUser.class);
    }
}
