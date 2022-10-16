package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.TeamCompleteResponse;
import br.com.cwi.crescer.api.mapper.TeamMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetDetailedTeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public TeamCompleteResponse getDetailedTeam() {

        Team team = userAccountAuthenticatedService.get().getTeam();

        return teamMapper.toResponseWithUsersAndRoadmaps(team);
    }
}
