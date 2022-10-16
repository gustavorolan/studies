package com.example.br.com.cwi.crescer.jpa.mapper;

import com.example.br.com.cwi.crescer.jpa.controller.response.OrderResponse;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toResponse (OrderFromUser entity){
        Integer totalPrice = entity.getItemList().stream().map((item) -> {
            return item.getProduct().getProductPrice()*item.getQuantity();
        }).reduce(0,(acc,price)->acc+price);
        entity.setTotalPrice(totalPrice);

        return new ModelMapper().map(entity, OrderResponse.class);
    }
}
