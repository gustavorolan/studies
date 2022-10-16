package br.com.cwi.crescer.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class LocalDateAndTimeServiceTest {
    @InjectMocks
    private LocalDateAndTimeService localDateAndTimeService;

    @Test
    @DisplayName("Should return today's date")
    void getLocalDate() {
        LocalDate localDate = localDateAndTimeService.getLocalDate();
        Assertions.assertEquals(LocalDate.now(),localDate);
    }

    @Test
    @DisplayName("Should return today's date and hour")
    void getLocalDateTime() {
        LocalDateTime localDateTime =localDateAndTimeService.getLocalDateTime();
        Assertions.assertEquals(LocalDate.now(),LocalDate.from(localDateTime));
        Assertions.assertEquals(LocalDateTime.now().getHour(),localDateTime.getHour());
        Assertions.assertEquals(LocalDateTime.now().getMinute(),localDateTime.getMinute());
    }
}