package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.*;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TeamMapperTest {
    @InjectMocks
    private TeamMapper teamMapper;

    @Mock
    private AttachmentMapper attachmentMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CourseMapper courseMapper;

    @Test
    @DisplayName("Should return a TeamResponseList")
    void toResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        Team team = TeamFactory.getBuilder().creator(userAccount).build();
        TeamResponse teamResponse = TeamFactory
                .getBuilderTeamResponse()
                .creatorId(userAccount.getId())
                .build();

        Mockito.when(attachmentMapper.toResponseAttachment(null)).thenReturn(null);

        List<TeamResponse> result = teamMapper.toResponse(List.of(team));

        Mockito.verify(attachmentMapper).toResponseAttachment(null);

        Assertions.assertEquals(List.of(teamResponse),result);
    }

    @Test
    @DisplayName("Should return a TeamResponse")
    void toEntity() {
        Team team = TeamFactory.getBuilder()
                .id(null).build();
        TeamRequest request = TeamFactory.getTeamRequest();

        Team result = teamMapper.toEntity(request);

        Assertions.assertEquals(team,result);
    }

    @Test
    @DisplayName("Should return a Team")
    void testToResponse() {
        Long id = 1L;
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        UserAccount userAccount = UserAccountFactory.get();

        Team team = TeamFactory.getBuilder()
                .creator(userAccount)
                .image(attachment)
                .build();
        TeamResponse teamResponse = TeamFactory.getBuilderTeamResponse()
                .creatorId(id)
                .image(attachmentResponseData)
                .build();

        Mockito.when(attachmentMapper.toResponseAttachment(team.getImage())).thenReturn(attachmentResponseData);

        TeamResponse result = teamMapper.toResponse(team);

        Mockito.verify(attachmentMapper).toResponseAttachment(team.getImage());

        Assertions.assertEquals(teamResponse,result);
    }

    @Test
    @DisplayName("Should return a TeamCompleteResponse")
    void toResponseWithUsersAndRoadmaps() {
        Long id = 1L;
        UserAccount user = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        Course course = CourseFactory.getCourseBuilder()
                .author(user)
                .build();

        CourseResponse courseResponse = CourseFactory.getCourseBuilderResponse()
                .authorId(id)
                .build();

        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        Team team = TeamFactory.getBuilder()
                .creator(user)
                .image(attachment)
                .userAccountList(List.of(user))
                .courses(List.of(course))
                .build();

        TeamCompleteResponse teamResponse = TeamFactory.getTeamCompleteResponseBuilder()
                .creatorId(id)
                .image(attachmentResponseData)
                .users(List.of(userResponse))
                .courses(List.of(courseResponse))
                .active(null)
                .build();

        Mockito.when(attachmentMapper.toResponseAttachment(team.getImage())).thenReturn(attachmentResponseData);
        Mockito.when(userMapper.toResponse(user)).thenReturn(userResponse);
        Mockito.when(courseMapper.toResponse(team.getCourses().get(0))).thenReturn(courseResponse);

        TeamCompleteResponse result = teamMapper.toResponseWithUsersAndRoadmaps(team);

        Mockito.verify(attachmentMapper).toResponseAttachment(team.getImage());
        Mockito.verify(userMapper).toResponse(user);
        Mockito.verify(courseMapper).toResponse(team.getCourses().get(0));

        Assertions.assertEquals(teamResponse,result);
    }
}