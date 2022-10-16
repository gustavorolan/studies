package com.example.br.com.cwi.crescer.jpa.controller.request;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CreditCardRequest {
    @NotEmpty
    private  String name;
    @NotEmpty
    private String creditCardNumber;
    @NotNull
    private  Integer verificationCode;
}
