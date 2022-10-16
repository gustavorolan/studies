package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.request.CreditCardRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.CreditCardResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.AddCreditCardMapper;
import com.example.br.com.cwi.crescer.jpa.model.CreditCard;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.repository.CreditCardRepository;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeCreditCardService {
    @Autowired
    AddCreditCardMapper addCreditCardMapper;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    private OrderFromUserRepository orderFromUserRepository;
    @Autowired
    private OrderFromUserFinderService orderFromUserFinderService;
    public CreditCardResponse change(CreditCardRequest request, Long id) {
        OrderFromUser orderFromUser = orderFromUserFinderService.findByIdWithException(id);
        CreditCard creditCard = addCreditCardMapper.toEntity(request);
        orderFromUser.setCreditCard(creditCard);
        creditCardRepository.save(creditCard);
        return addCreditCardMapper.toResponse(creditCard);
    }
}
