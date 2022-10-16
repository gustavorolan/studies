package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.RegisteredCoursesResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ValidateIfActiveValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetRegisteredCoursesServiceTest {

    @InjectMocks
    private GetRegisteredCoursesService getRegisteredCoursesService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ValidateIfActiveValidator validateIfActiveValidator;

    @Mock
    private UserAccountAuthenticatedService accountAuthenticatedService;

    @Test
    @DisplayName("Should return registered courses")
    void getCourses() {
        UserAccount userAccount = UserAccountFactory.get();
        RegisteredCoursesResponse userResponse = UserAccountFactory
                .getBuilderUserWithCourses()
                .build();

        Mockito.when(accountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(userMapper.toResponseWithCourses(userAccount)).thenReturn(userResponse);

        RegisteredCoursesResponse courses = getRegisteredCoursesService.getCourses();

        Mockito.verify(accountAuthenticatedService).get();
        Mockito.verify(userMapper).toResponseWithCourses(userAccount);
        Mockito.verify(validateIfActiveValidator)
                .validateIfActive(userAccount.getActive());

        Assertions.assertEquals(userResponse,courses);
    }
}