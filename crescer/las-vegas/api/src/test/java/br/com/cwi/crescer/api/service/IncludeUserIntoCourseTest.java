package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.IncludeUserIntoCourseRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import br.com.cwi.crescer.api.validator.VerifyIfIsManagerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IncludeUserIntoCourseTest {
    @InjectMocks
    private IncludeUserIntoCourse includeUserIntoCourse;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private VerifyIfIsManagerValidator verifyIfIsManagerValidator;

    @Mock
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Captor
    private ArgumentCaptor<UserAccount> argumentCaptor;

    @Test
    @DisplayName("Should include user in some team")
    void include() {
        IncludeUserIntoCourseRequest request = IncludeUserIntoCourseRequest.builder()
                .build();

        UserAccount user = UserAccountFactory.getBuilder().build();

        Course course = CourseFactory.getCourseBuilder()
                .build();

        request.setEmail(user.getEmail());


        UserAccount userAccountSaved = UserAccountFactory.getBuilder()
                .build();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("You have added " + user.getFullName() + " to " + course.getName())
                .build();

        Mockito.when(findUserWithThrow.findByEmailWithException(request.getEmail()))
                .thenReturn(user);
        Mockito.when(findCourseWithThrow.findByIdWithException(course.getId()))
                .thenReturn(course);

        ResponseMessage result = includeUserIntoCourse.include(course.getId(), request);

        Mockito.verify(findUserWithThrow).findByEmailWithException(request.getEmail());
        Mockito.verify(findCourseWithThrow).findByIdWithException(course.getId());
        Mockito.verify(invalidateSelfOpperationValidator).invalidateSelfOpperation(user);
        Mockito.verify(verifyIfIsManagerValidator).verifyIsManager();
        Mockito.verify(userAccountRepository).save(argumentCaptor.capture());


        Assertions.assertSame(userAccountSaved.getFullName(),argumentCaptor.getValue().getFullName());
        Assertions.assertEquals(responseMessage,result);

    }
}