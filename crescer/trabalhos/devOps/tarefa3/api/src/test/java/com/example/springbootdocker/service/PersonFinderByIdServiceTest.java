package com.example.springbootdocker.service;

import com.example.springbootdocker.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonFinderByIdServiceTest {
    @InjectMocks
    private PersonFinderByIdService personFinderByIdService;
    @Mock
    private PersonRepository personRepository;
    @Test
    @DisplayName("Deve retornar erro ao nao encontrar pessoa")
    void findByIdWithException() {

        Exception exception = Assertions.assertThrows(ResponseStatusException.class, () ->
                personFinderByIdService.findByIdWithException(1L));

        ResponseStatusException expectedResult = new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa nao existe");

        Assertions.assertEquals(expectedResult.getMessage(), exception.getMessage());
    }
}