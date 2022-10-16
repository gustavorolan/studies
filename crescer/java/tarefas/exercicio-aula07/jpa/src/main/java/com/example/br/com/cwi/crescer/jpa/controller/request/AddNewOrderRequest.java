package com.example.br.com.cwi.crescer.jpa.controller.request;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class AddNewOrderRequest {
    @NotEmpty
    private String responsibleName;
}
