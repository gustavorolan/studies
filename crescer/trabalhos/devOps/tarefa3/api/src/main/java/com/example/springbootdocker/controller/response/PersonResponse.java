package com.example.springbootdocker.controller.response;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PersonResponse {
    private Long personId;

    private String name;

    private String image;

    private Integer votesCounter;

}
