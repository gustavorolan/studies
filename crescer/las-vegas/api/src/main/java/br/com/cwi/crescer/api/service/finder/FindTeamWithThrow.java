package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindTeamWithThrow {
    @Autowired
    private TeamRepository teamRepository;

    private static final String RESPONSE = "Team does not exist";

    public Team findByIdWithException(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Team findByIdAndActiveWithException(Long id, Boolean active) {
        return teamRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
