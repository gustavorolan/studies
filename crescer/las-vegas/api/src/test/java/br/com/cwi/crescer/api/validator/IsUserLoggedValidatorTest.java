package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
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
class IsUserLoggedValidatorTest {
    @InjectMocks
    private IsUserLoggedValidator isUserLoggedValidator;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("Verifies if user is online")
    void verify() {
        UserAccount userAccount = UserAccountFactory.get();

        UserAccount userAccountTwo = UserAccountFactory.getBuilder()
                .id(2L)
                .fullName("other")
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            isUserLoggedValidator.verify(userAccountTwo);
        });

        Mockito.verify(userAccountAuthenticatedService).get();

        Assertions.assertEquals("400 BAD_REQUEST \"User is not logged\"", exception.getMessage());

    }
}