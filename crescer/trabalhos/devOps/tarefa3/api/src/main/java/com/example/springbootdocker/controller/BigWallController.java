package com.example.springbootdocker.controller;

import com.example.springbootdocker.controller.request.VoteRequest;
import com.example.springbootdocker.controller.response.PersonResponse;
import com.example.springbootdocker.controller.response.StringResponse;
import com.example.springbootdocker.service.GetPeopleService;
import com.example.springbootdocker.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bigWall")
public class BigWallController {
    @Autowired
    private VoteService voteService;

    @Autowired
    private GetPeopleService getPeopleService;

    @PostMapping("/vote/{personId}")
    public StringResponse vote(@PathVariable Long personId, @Valid @RequestBody VoteRequest voteRequest) {
        return voteService.vote(personId, voteRequest);
    }
    @GetMapping("/people")
    public List<PersonResponse> vote() {
        return getPeopleService.get();
    }
}
