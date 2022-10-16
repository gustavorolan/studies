package com.example.br.com.cwi.crescer.jpa.controller.request;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class ItemRequest {
    @NotNull
    private Long prodcutId;
    @NotNull
    private Integer quantity;
}
