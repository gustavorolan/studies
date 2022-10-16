package com.example.springbootdocker.service;

import com.example.springbootdocker.controller.request.VoteRequest;
import com.example.springbootdocker.controller.response.StringResponse;
import com.example.springbootdocker.model.Person;
import com.example.springbootdocker.model.Vote;
import com.example.springbootdocker.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class VoteService {
    @Autowired
    private VotesRepository votesRepository;
    @Autowired
    private PersonFinderByIdService personFinderByIdService;

    public StringResponse vote(Long personId, @Valid VoteRequest voteRequest) {
       Person person =  personFinderByIdService.findByIdWithException(personId);
       Vote vote =Vote.builder()
               .name(voteRequest.getName())
               .person(person)
               .build();
       person.getVoteList().add(vote);
       person.setVotesCounter(person.getVoteList().toArray().length);
       votesRepository.save(vote);
       return StringResponse.builder().message("Você votou com sucesso em "+person.getName()).build();
    }
}
