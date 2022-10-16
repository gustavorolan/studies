package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
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
class GetUserAuthenticatedTest {

    @InjectMocks
    private GetUserAuthenticated getUserAuthenticated;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ValidateIfActiveValidator validateIfActiveValidator;

    @Test
    @DisplayName("Should return user authenticated")
    void get() {

        UserAccount userAccount = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(userMapper.toResponse(userAccount)).thenReturn(userResponse);

        UserResponse user = getUserAuthenticated.get();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(userMapper).toResponse(userAccount);
        Mockito.verify(validateIfActiveValidator)
                .validateIfActive(userAccount.getActive());

        Assertions.assertEquals(userResponse,user);
    }
}