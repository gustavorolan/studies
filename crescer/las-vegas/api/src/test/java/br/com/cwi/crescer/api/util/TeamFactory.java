package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.TeamCompleteResponse;
import br.com.cwi.crescer.api.controller.response.TeamResponse;
import br.com.cwi.crescer.api.model.Team;

public class TeamFactory {
    public static Team.TeamBuilder getBuilder(){
        return Team.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .active(true);
    }
    public static Team get(){
        return getBuilder().build();
    }

    public static TeamResponse.TeamResponseBuilder getBuilderTeamResponse(){
        return TeamResponse.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .active(true);
    }
    public static TeamResponse getTeamResponse(){
        return getBuilderTeamResponse().build();
    }

    public static TeamRequest getTeamRequest(){
        return TeamRequest.builder()
                .description("desc")
                .name("name")
                .build();
    }
    public static TeamCompleteResponse getTeamCompleteResponse(){
        return getTeamCompleteResponseBuilder().build();
    }

    public static TeamCompleteResponse.TeamCompleteResponseBuilder getTeamCompleteResponseBuilder(){
        return TeamCompleteResponse.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .active(true) ;
    }
}
