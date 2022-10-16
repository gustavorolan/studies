package com.example.springbootdocker.controller.request;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoteRequest {
    @NotEmpty
    private String name;
}
