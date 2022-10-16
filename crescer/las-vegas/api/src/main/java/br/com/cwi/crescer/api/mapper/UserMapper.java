package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.RegisteredCoursesResponse;
import br.com.cwi.crescer.api.controller.response.UserCompleteResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeamMapper teamMapper;

    public UserResponse toResponse(UserAccount userAccount) {
        return UserResponse.builder()
                .id(userAccount.getId())
                .fullName(userAccount.getFullName())
                .email(userAccount.getEmail())
                .assessment(userAccount.getAssessment())
                .permission(userAccount.getPermission())
                .presentation(userAccount.getPresentation())
                .relevantLinks(userAccount.getRelevantLinks())
                .imageId(userAccount.getProfileImage().getId())
                .build();
    }

    public RegisteredCoursesResponse toResponseWithCourses(UserAccount userAccount) {
        return RegisteredCoursesResponse.builder()
                .id(userAccount.getId())
                .fullName(userAccount.getFullName())
                .email(userAccount.getEmail())
                .assessment(userAccount.getAssessment())
                .permission(userAccount.getPermission())
                .presentation(userAccount.getPresentation())
                .relevantLinks(userAccount.getRelevantLinks())
                .imageId(userAccount.getProfileImage().getId())
                .registeredCoursesResponseList(userAccount.getMyCourses()
                        .stream()
                        .map(course -> courseMapper.toResponse(course))
                        .collect(Collectors.toList()))
                .build();
    }

    public UserCompleteResponse toResponseWithCoursesAndTeams(UserAccount userAccount) {
        return UserCompleteResponse.builder()
                .id(userAccount.getId())
                .fullName(userAccount.getFullName())
                .email(userAccount.getEmail())
                .assessment(userAccount.getAssessment())
                .permission(userAccount.getPermission())
                .presentation(userAccount.getPresentation())
                .relevantLinks(userAccount.getRelevantLinks())
                .imageId(userAccount.getProfileImage().getId())
                .courses(userAccount.getCreatedCourses()
                        .stream()
                        .map(course -> courseMapper.toResponse(course))
                        .collect(Collectors.toList()))
                .build();
    }

    public List<UserResponse> toResponse(List<UserAccount> userAccounts) {
        return userAccounts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserAccount toEntity(RegisterUserRequest request) {
        return new ModelMapper().map(request, UserAccount.class);
    }
}
