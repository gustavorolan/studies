package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @InjectMocks
    private UpdateUserService updateUserService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Captor
    private ArgumentCaptor<UserAccount> userAccountArgumentCaptor;

    @Test
    @DisplayName("Should update user")
    void editUser() {
        UserAccount userAccount = UserAccountFactory.get();
        RegisterUserRequest request = RegisterUserRequest.builder()
                .email("newEmail@gmail.com")
                .password("1234")
                .fullName("new name").build();

        UserAccount userAccountSaved = UserAccountFactory.getBuilder()
                .email("newEmail@gmail.com")
                .password("1234")
                .fullName("new name").build();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("User updated successfully").build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        ResponseMessage result = updateUserService.editUser(request);

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(userAccountRepository)
                .save(userAccountArgumentCaptor.capture());

        Assertions.assertSame(userAccountSaved.getFullName() , userAccountArgumentCaptor.getValue().getFullName());
        Assertions.assertSame(userAccountSaved.getPassword() , userAccountArgumentCaptor.getValue().getPassword());
        Assertions.assertSame(userAccountSaved.getEmail() , userAccountArgumentCaptor.getValue().getEmail());


    }
}