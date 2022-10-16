package br.com.cwi.crescer.oldflix.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DateServiceTest {
    @InjectMocks
    private DateService dateService;

    @Test
    @DisplayName("Should Return Date")
    void shouldReturnADate() {
    LocalDate result = dateService.now();
    assertEquals(LocalDate.now(),result);
    }
}