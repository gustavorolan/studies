package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Permission;
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
class VerifyIfIsManagerValidatorTest {
    @InjectMocks
    private VerifyIfIsManagerValidator verify;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("verify if user is manager")
    void verifyIsManager() {
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .permission(Permission.NORMAL)
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            verify.verifyIsManager();
        });
        Mockito.verify(userAccountAuthenticatedService).get();

        Assertions.assertEquals("401 UNAUTHORIZED \"Unauthorized opperation\"", exception.getMessage());

    }
}