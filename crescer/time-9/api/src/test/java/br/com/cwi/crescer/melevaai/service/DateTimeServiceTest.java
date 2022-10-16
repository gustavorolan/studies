package br.com.cwi.crescer.melevaai.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DateTimeServiceTest {
    @InjectMocks
    private DateTimeService dateTimeService;

    @Test
    @DisplayName("Should be equal date")
    void dateNowTest() {
        LocalDate date = dateTimeService.dateNow();
        assertEquals(LocalDate.now(), date);
    }

    @Test
    @DisplayName("Should be almost equal time")
    void dateAndTimeNow() {
        LocalDateTime dateTime = dateTimeService.dateAndTimeNow();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        assertEquals(LocalDateTime.now().getHour(), hour);
        assertEquals(LocalDateTime.now().getMinute(), minute);
    }
}