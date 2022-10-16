package com.example.springbootdocker.repository;

import com.example.springbootdocker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
