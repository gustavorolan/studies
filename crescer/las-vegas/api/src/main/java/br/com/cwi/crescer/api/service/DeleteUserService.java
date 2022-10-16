package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    public ResponseMessage deleteUser(Long userId) {
        UserAccount userAccount = userAccountAuthenticatedService.get();

        permissionValidator.validateManagerPermission(userAccount);

        UserAccount user = findUserWithThrow.findByIdAndActiveWithException(userId, true);

        userAccountRepository.delete(user);

        return ResponseMessage.builder().response("You've deleted successfully").build();
    }
}
