package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserEmailValidatorTest {
    @InjectMocks
    private UserEmailValidator userEmailValidator;
    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("Verifies if email is already in use")
    void validateIfUserEmailIsInUse() {
        UserAccount userAccount = UserAccountFactory.get();
        String email = "email@email.com";
        Mockito.when(userAccountRepository.findByEmail(email)).thenReturn(Optional.of(userAccount));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userEmailValidator.validateIfUserEmailIsInUse(email);
        });
        Mockito.verify(userAccountRepository).findByEmail(email);
        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Email already in use\"", exception.getMessage());
    }
}