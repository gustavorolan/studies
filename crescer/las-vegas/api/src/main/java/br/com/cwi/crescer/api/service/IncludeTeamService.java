package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.TeamResponse;
import br.com.cwi.crescer.api.mapper.TeamMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;

@Service
public class IncludeTeamService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private FindAttachmentWithThrow findAttachmentWithThrow;

    public TeamResponse includeTeam(TeamRequest request, String imageId) {
        parametersRegexValidator.verifyIfImageIdIsValid(imageId);

        UserAccount loggedUser = userAccountAuthenticatedService.get();

        permissionValidator.validateManagerPermission(loggedUser);

        Attachment attachment = findAttachmentWithThrow.findByIdWithException(imageId);

        Team team = teamMapper.toEntity(request);

        Course course = new Course();
        course.setAuthor(loggedUser);
        course.setImage(attachment);
        course.setDateTimeCreation(LocalDateTime.now());
        course.setActive(true);
        course.setAssessment(SCORE.getScore());
        course.setApprovementStatus(true);
        loggedUser.getCreatedCourses().add(course);
        course.setAuthor(loggedUser);
        course.setDescription("OnBoarding");
        course.setName("Esse é seu primeiro curso. Edite-o quando quiser.");

        team.setCreator(loggedUser);
        team.setCourses(List.of(course));
        team.getUserAccountList().add(loggedUser);
        team.setImage(attachment);
        loggedUser.setCreatedTeam(team);
        loggedUser.setTeam(team);

        teamRepository.save(team);
        return teamMapper.toResponse(team);
    }}
