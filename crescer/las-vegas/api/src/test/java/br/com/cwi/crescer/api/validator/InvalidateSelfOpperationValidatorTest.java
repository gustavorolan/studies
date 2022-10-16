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
class InvalidateSelfOpperationValidatorTest {
    @InjectMocks
    private  InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("Don't allow to self operate")
    void invalidateSelfOpperation() {
        UserAccount userAccount = UserAccountFactory.get();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            invalidateSelfOpperationValidator.invalidateSelfOpperation(userAccount);
        });

        Mockito.verify(userAccountAuthenticatedService).get();

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Invalid opperation\"", exception.getMessage());
    }
}