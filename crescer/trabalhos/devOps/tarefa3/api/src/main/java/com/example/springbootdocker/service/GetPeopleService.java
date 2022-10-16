package com.example.springbootdocker.service;

import com.example.springbootdocker.controller.response.PersonResponse;
import com.example.springbootdocker.model.Person;
import com.example.springbootdocker.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPeopleService {
    @Autowired
    private PersonRepository personRepository;
    public List<PersonResponse> get() {
        return  personRepository.findAll().stream().map(person -> {
            return  PersonResponse.builder()
                    .name(person.getName())
                    .image(person.getImage())
                    .votesCounter(person.getVotesCounter())
                    .personId(person.getPersonId())
                    .build();
        }).sorted(Comparator.comparing(PersonResponse::getPersonId)).collect(Collectors.toList());
    }
}
