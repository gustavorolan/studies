package com.example.springbootdocker.repository;

import com.example.springbootdocker.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository extends JpaRepository<Vote, Long> {
}
