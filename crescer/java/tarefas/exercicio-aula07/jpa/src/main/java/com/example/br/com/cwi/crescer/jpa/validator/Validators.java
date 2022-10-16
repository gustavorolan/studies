package com.example.br.com.cwi.crescer.jpa.validator;


import com.example.br.com.cwi.crescer.jpa.model.OrderFromUser;
import com.example.br.com.cwi.crescer.jpa.model.Situation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class Validators {
    public void IfOrderIsOpenThrowsAnException(OrderFromUser orderFromUser){
        if(orderFromUser.getSituation().equals(Situation.CLOSED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"pedido esta fechado");
        }
    }
    public void ifItemsListIsNullThrowsAnException (OrderFromUser orderFromUser){
        if(orderFromUser.getItemList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lista de compras esta vazia");
        }
    }

    public void ifCreditCardIsNullThrowsAnException (OrderFromUser orderFromUser){
        if(orderFromUser.getCreditCard()==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"voce nao cadastrou cartao de credito");
        }
    }

    public void ifCreditCardIsNotNullThrowsAnException (OrderFromUser orderFromUser){
        if(orderFromUser.getCreditCard()!=null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"voce já cadastrou cartao de credito nesse pedido");
        }
    }

    public void ifSomethingIsNotFilledThrowsAnException(OrderFromUser orderFromUser) {
        ifItemsListIsNullThrowsAnException(orderFromUser);
        ifCreditCardIsNullThrowsAnException(orderFromUser);
    }
}
