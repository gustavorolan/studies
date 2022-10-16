package com.example.springbootdocker.service;

import com.example.springbootdocker.controller.request.VoteRequest;
import com.example.springbootdocker.model.Person;
import com.example.springbootdocker.model.Vote;
import com.example.springbootdocker.repository.VotesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {
    @InjectMocks
    private  VoteService voteService;
    @Mock
    private PersonFinderByIdService personFinderByIdService;
    @Mock
    private VotesRepository votesRepository;
    @Captor
    private ArgumentCaptor<Vote> voteCaptor;
    @Test
    void vote() {
        Long personId= 1L;
        VoteRequest voteRequest=VoteRequest.builder()
                .name("randomPerson")
                .build();

        Person personOne = Person.builder()
                .name("personOne")
                .personId(1L)
                .image("")
                .votesCounter(0)
                .voteList(new ArrayList<>())
                .build();

        Vote resultVote = Vote.builder()
                .name(voteRequest.getName())
                .person(personOne)
                .build();

        Mockito.when(personFinderByIdService.findByIdWithException(personId)).thenReturn(personOne);

        voteService.vote(personId,voteRequest);

        Mockito.verify(personFinderByIdService).findByIdWithException(personId);
        Mockito.verify(votesRepository).save(voteCaptor.capture());

        Vote result = voteCaptor.getValue();

        Assertions.assertEquals(resultVote,result);
    }
}