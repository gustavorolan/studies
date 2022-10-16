package com.example.springbootdocker.service;

import com.example.springbootdocker.model.Person;
import com.example.springbootdocker.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PersonFinderByIdService {
    @Autowired
    private PersonRepository personRepository;

    public Person findByIdWithException(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pessoa nao existe"));
    }
}
