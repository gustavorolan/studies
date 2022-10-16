package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.TeamResponse;
import br.com.cwi.crescer.api.controller.response.TeamCompleteResponse;
import br.com.cwi.crescer.api.model.Team;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private CourseMapper courseMapper;

    public List<TeamResponse> toResponse(List<Team> teams) {
        return teams.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Team toEntity(TeamRequest request) {
        Team team = new ModelMapper().map(request, Team.class);
        team.setDateTimeCreation(LocalDateTime.now());
        team.setUserAccountList(new ArrayList<>());
        team.setActive(true);
        return team;
    }

    public TeamResponse toResponse(Team team){
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .image(attachmentMapper.toResponseAttachment(team.getImage()))
                .active(team.getActive())
                .creatorId(team.getCreator().getId())
                .build();
    }

    public TeamCompleteResponse toResponseWithUsersAndRoadmaps(Team team) {
        return TeamCompleteResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .image(attachmentMapper.toResponseAttachment(team.getImage()))
                .creatorId(team.getCreator().getId())
                .courses(team.getCourses()
                        .stream()
                        .map(course -> courseMapper.toResponse(course))
                        .collect(Collectors.toList()))
                .users(team.getUserAccountList()
                        .stream()
                        .map(user -> userMapper.toResponse(user))
                        .collect(Collectors.toList()))
                .build();
    }
}
