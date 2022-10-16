package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private UserAccountRepository userAccountRepository;


    public ResponseMessage editUser(RegisterUserRequest request) {
        UserAccount user = userAccountAuthenticatedService.get();

        user.setFullName(request.getFullName().isBlank() ? user.getFullName() : request.getFullName());
        user.setEmail(request.getEmail().isBlank() ? user.getEmail() : request.getEmail());
        user.setPassword(request.getPassword().isBlank() ? user.getPassword() : request.getPassword());

        userAccountRepository.save(user);

        return ResponseMessage.builder().response("User updated successfully").build();
    }
}
