package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

    @InjectMocks
    private DeleteUserService deleteUserService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Test
    @DisplayName("Should delete an user")
    void deleteUser() {
        UserAccount userAccount = UserAccountFactory.get();
        UserAccount userToDelete = UserAccountFactory.getBuilder().id(2L).build();
        ResponseMessage expected = ResponseMessage.builder().response("You've deleted successfully").build();
        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findUserWithThrow.findByIdAndActiveWithException(userToDelete.getId(), true)).thenReturn(userToDelete);

        ResponseMessage responseMessageBuilder = deleteUserService.deleteUser(userToDelete.getId());

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findUserWithThrow).findByIdAndActiveWithException(userToDelete.getId(), true);
        Mockito.verify(permissionValidator).validateManagerPermission(userAccount);
        Mockito.verify(userAccountRepository).delete(userToDelete);

        Assertions.assertEquals(expected,responseMessageBuilder);
    }
}