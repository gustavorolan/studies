package com.example.br.com.cwi.crescer.jpa.controller.response;

import com.example.br.com.cwi.crescer.jpa.model.CreditCard;
import com.example.br.com.cwi.crescer.jpa.model.Item;
import com.example.br.com.cwi.crescer.jpa.model.Situation;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private Long orderId;

    private String responsibleName;

    private Integer totalPrice;

    private Situation situation;

    private List<Item> itemList;

    private CreditCard creditCard;

}
