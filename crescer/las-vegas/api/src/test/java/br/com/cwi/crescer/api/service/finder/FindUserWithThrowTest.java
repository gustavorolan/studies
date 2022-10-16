package br.com.cwi.crescer.api.service.finder;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FindUserWithThrowTest {

    @InjectMocks
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("Should find user by id")
    void findById() {
        Long id = 1L;

        UserAccount user = UserAccountFactory.get();

        Mockito.when(userAccountRepository.findById(id)).
                thenReturn(Optional.ofNullable(user));

        UserAccount value = findUserWithThrow.findByIdWithException(id);

        Mockito.verify(userAccountRepository).findById(id);

        Assertions.assertEquals(user, value);
    }

    @Test
    @DisplayName("Should throw an exception, because user was not found with id search")
    void findByIdWithException() {
        Long id = 1L;

        Mockito.when(userAccountRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findUserWithThrow.findByIdWithException(id);
        });

        Mockito.verify(userAccountRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"User does not exist\"", exception.getMessage());

    }

    @Test
    @DisplayName("Should find user by email")
    void findByEmail() {
        String email = "email";

        UserAccount user = UserAccountFactory.get();

        Mockito.when(userAccountRepository.findByEmail(email)).thenReturn(Optional.ofNullable(user));

        UserAccount value = findUserWithThrow.findByEmailWithException(email);

        Mockito.verify(userAccountRepository).findByEmail(email);

        Assertions.assertEquals(user, value);

    }

    @Test
    @DisplayName("Should throw an exception, because user was not found with email search")
    void findByEmailWithException() {
        String email = "email";

        Mockito.when(userAccountRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findUserWithThrow.findByEmailWithException(email);
        });

        Mockito.verify(userAccountRepository).findByEmail(email);

        Assertions.assertEquals("404 NOT_FOUND \"User does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find user not in team")
    void findByIfTeamContainsNot() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        UserAccount user = UserAccountFactory.get();
        UserAccount outsider = UserAccountFactory.get();

        Page<UserAccount> users = new PageImpl<UserAccount>(List.of(outsider));

        Mockito.when(userAccountRepository.findOthers(List.of(user.getId()), pageable))
                .thenReturn(Optional.of(users));

        Page<UserAccount> usersResponse = findUserWithThrow.findByIfMyTeamsContainsNot(pageable, List.of(outsider.getId()));

        Mockito.verify(userAccountRepository).findOthers(List.of(user.getId()), pageable);

        Assertions.assertEquals(users, usersResponse);
    }

    @Test
    @DisplayName("Should throw an exception, because user outside time was not found")
    void findByIdContainsNotWithThrow() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        UserAccount user = UserAccountFactory.get();

        Mockito.when(userAccountRepository.findOthers(List.of(user.getId()), pageable))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findUserWithThrow.findByIfMyTeamsContainsNot(pageable, List.of(user.getId()));
        });

        Mockito.verify(userAccountRepository).findOthers(List.of(user.getId()), pageable);

        Assertions.assertEquals("404 NOT_FOUND \"User does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find user with id not int id list")
    void findByIdContainsNot() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        UserAccount user = UserAccountFactory.get();
        UserAccount outsider = UserAccountFactory.get();

        Page<UserAccount> users = new PageImpl<UserAccount>(List.of(outsider));

        Mockito.when(userAccountRepository.findOthers(List.of(user.getId()), pageable))
                .thenReturn(Optional.of(users));

        Page<UserAccount> usersResponse = findUserWithThrow.findByIdContainsNotWithException(pageable, List.of(outsider.getId()));

        Mockito.verify(userAccountRepository).findOthers(List.of(user.getId()), pageable);

        Assertions.assertEquals(users, usersResponse);
    }

    @Test
    @DisplayName("Should throw an exception, because user outside time was not found by id")
    void findByIdContainsNotWithError() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        UserAccount user = UserAccountFactory.get();

        Mockito.when(userAccountRepository.findOthers(List.of(user.getId()), pageable))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
             findUserWithThrow.findByIdContainsNotWithException(pageable, List.of(user.getId()));
        });

        Mockito.verify(userAccountRepository).findOthers(List.of(user.getId()), pageable);

        Assertions.assertEquals("404 NOT_FOUND \"User does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find user with id and active parameter")
    void findByIdAndActive() {
        Long id = 1L;
        Boolean active = true;

        UserAccount user = UserAccountFactory.get();

        Mockito.when(userAccountRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.of(user));

        UserAccount value = findUserWithThrow.findByIdAndActiveWithException(id, active);

        Mockito.verify(userAccountRepository).findByIdAndActive(id, active);

        Assertions.assertEquals(user, value);
    }

    @Test
    @DisplayName("Should throw error when attempting to find user by id and active parameter")
    void findByIdAndActiveWithError() {
        Long id = 1L;
        Boolean active = true;

        Mockito.when(userAccountRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findUserWithThrow.findByIdAndActiveWithException(id, active);
        });

        Mockito.verify(userAccountRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"User does not exist\"", exception.getMessage());
    }
}