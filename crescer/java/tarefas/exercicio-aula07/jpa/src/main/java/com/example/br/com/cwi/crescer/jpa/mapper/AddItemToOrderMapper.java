package com.example.br.com.cwi.crescer.jpa.mapper;

import com.example.br.com.cwi.crescer.jpa.controller.request.AddNewOrderRequest;
import com.example.br.com.cwi.crescer.jpa.controller.request.ItemRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.AddNewOrderResponse;
import com.example.br.com.cwi.crescer.jpa.controller.response.ItemResponse;
import com.example.br.com.cwi.crescer.jpa.model.Item;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddItemToOrderMapper {
    public ItemResponse toResponse(Item entity){
        return new ModelMapper().map(entity,ItemResponse.class);
    }
}
