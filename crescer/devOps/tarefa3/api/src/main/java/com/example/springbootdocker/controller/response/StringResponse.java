package com.example.springbootdocker.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StringResponse {
    private String message;
}
