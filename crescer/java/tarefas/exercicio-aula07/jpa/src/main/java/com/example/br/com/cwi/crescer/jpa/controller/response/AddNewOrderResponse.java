package com.example.br.com.cwi.crescer.jpa.controller.response;

import com.example.br.com.cwi.crescer.jpa.model.Situation;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class AddNewOrderResponse {
    private String responsibleName;
    private Long orderId;
    private Situation situation;
}
