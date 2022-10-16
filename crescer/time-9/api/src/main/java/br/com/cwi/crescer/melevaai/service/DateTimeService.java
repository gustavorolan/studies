package br.com.cwi.crescer.melevaai.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class DateTimeService {
    public LocalDate dateNow(){
        return  LocalDate.now();
    }
    public LocalDateTime dateAndTimeNow(){
        return  LocalDateTime.now();
    }

}
