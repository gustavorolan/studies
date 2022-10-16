package com.example.br.com.cwi.crescer.jpa.service;

import com.example.br.com.cwi.crescer.jpa.controller.request.CreditCardRequest;
import com.example.br.com.cwi.crescer.jpa.controller.response.CreditCardResponse;
import com.example.br.com.cwi.crescer.jpa.mapper.AddCreditCardMapper;
import com.example.br.com.cwi.crescer.jpa.model.CreditCard;
import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.repository.CreditCardRepository;
import com.example.br.com.cwi.crescer.jpa.repository.OrderFromUserRepository;
import com.example.br.com.cwi.crescer.jpa.validator.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddNewCreditCardService {
    @Autowired
    AddCreditCardMapper addCreditCardMapper;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    private OrderFromUserRepository orderFromUserRepository;
    @Autowired
    private OrderFromUserFinderService orderFromUserFinderService;
    @Autowired
    private Validators validators;

    public CreditCardResponse add(CreditCardRequest request, Long id) {
        OrderFromUser  orderFromUser = orderFromUserFinderService.findByIdWithException(id);
        validators.ifCreditCardIsNotNullThrowsAnException(orderFromUser);
        CreditCard creditCard = addCreditCardMapper.toEntity(request);
        orderFromUser.setCreditCard(creditCard);
        creditCardRepository.save(creditCard);
        return addCreditCardMapper.toResponse(creditCard);
    }
}
