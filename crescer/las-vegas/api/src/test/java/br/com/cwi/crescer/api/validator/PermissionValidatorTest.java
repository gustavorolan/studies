package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Permission;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
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

@ExtendWith(MockitoExtension.class)
class PermissionValidatorTest {

    @InjectMocks
    private PermissionValidator permissionValidator;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;


    @Test
    @DisplayName("Verifies if user is course author")
    void validateLoggedUserPermission() {
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .permission(Permission.NORMAL)
                .id(2L)
                .fullName("other")
                .build();

        UserAccount userAccountTwo = UserAccountFactory.get();

        Course course = CourseFactory.getCourseBuilder()
                .author(userAccountTwo)
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            permissionValidator.validateLoggedUserPermission(course.getAuthor());
        });

        Mockito.verify(userAccountAuthenticatedService).get();

        Assertions.assertEquals("401 UNAUTHORIZED \"Unauthorized\"", exception.getMessage());

    }

    @Test
    @DisplayName("Verifies if user has manager permission")
    void validateManagerPermission() {
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .permission(Permission.MANAGER)
                .build();

        Boolean validateManagerPermission = permissionValidator.validateManagerPermission(userAccount);

        Assertions.assertTrue(validateManagerPermission);
    }

    @Test
    @DisplayName("Verifies if user is Team author")
    void validateLoggedUserPermissionForTeam() {
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .permission(Permission.MANAGER)
                .id(2L)
                .fullName("other")
                .build();

        UserAccount userAccountTwo = UserAccountFactory.get();

        Team team = TeamFactory.getBuilder()
                .creator(userAccountTwo)
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            permissionValidator.validateLoggedUserPermission(team.getCreator());
        });

        Mockito.verify(userAccountAuthenticatedService).get();

        Assertions.assertEquals("401 UNAUTHORIZED \"Unauthorized\"", exception.getMessage());

    }


}