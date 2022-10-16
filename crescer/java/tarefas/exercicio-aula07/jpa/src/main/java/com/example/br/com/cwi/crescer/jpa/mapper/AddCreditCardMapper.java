package com.example.br.com.cwi.crescer.jpa.mapper;

import com.example.br.com.cwi.crescer.jpa.controller.request.CreditCardRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.CreditCardResponse;
import com.example.br.com.cwi.crescer.jpa.model.CreditCard;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddCreditCardMapper {
    public CreditCardResponse toResponse(CreditCard entity){
        return new ModelMapper().map(entity, CreditCardResponse.class);
    }
    public CreditCard  toEntity(CreditCardRequest request){
        return  new ModelMapper().map(request,CreditCard.class);
    }
}
