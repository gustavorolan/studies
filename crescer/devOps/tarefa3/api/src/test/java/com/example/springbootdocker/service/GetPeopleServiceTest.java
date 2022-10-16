package com.example.springbootdocker.service;

import com.example.springbootdocker.controller.response.PersonResponse;
import com.example.springbootdocker.model.Person;
import com.example.springbootdocker.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class GetPeopleServiceTest {
    @InjectMocks
    private  GetPeopleService getPeopleService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return All from repository")
    void get() {
        Person personOne = Person.builder()
                .name("personOne")
                .personId(1L)
                .image("")
                .votesCounter(0)
                .build();
        PersonResponse personResponseOne = PersonResponse.builder()
                .name("personOne")
                .personId(1L)
                .image("")
                .votesCounter(0)
                .build();



        Person personTwo = Person.builder()
                .name("personTwo")
                .personId(2L)
                .image("")
                .votesCounter(0)
                .build();
        PersonResponse personResponseTwo = PersonResponse.builder()
                .name("personTwo")
                .personId(2L)
                .image("")
                .votesCounter(0)
                .build();


        List<Person> personList = new ArrayList<>();
        personList.add(personOne);
        personList.add(personTwo);

        List<PersonResponse>personResponseList=new ArrayList<>();
        personResponseList.add(personResponseOne);
        personResponseList.add(personResponseTwo);

        Mockito.when(personRepository.findAll()).thenReturn(personList);

        List<PersonResponse> result = getPeopleService.get();

        Mockito.verify(personRepository).findAll();

        Assertions.assertEquals(personResponseList,result);

    }
}