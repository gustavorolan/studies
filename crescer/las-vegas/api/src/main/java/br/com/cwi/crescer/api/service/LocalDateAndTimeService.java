package br.com.cwi.crescer.api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LocalDateAndTimeService {

    public LocalDate getLocalDate (){
        return LocalDate.now();
    }

    public LocalDateTime getLocalDateTime (){
        return LocalDateTime.now();
    }
}
