package br.com.cwi.crescer.api.security.controller;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.service.DeleteUserService;
import br.com.cwi.crescer.api.service.UserAccountService;
import br.com.cwi.crescer.api.service.UpdateUserService;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import br.com.cwi.crescer.api.validator.UserEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private UserEmailValidator userEmailValidator;

    @PostMapping("/register")
    public ResponseMessage include(@Valid @RequestParam("fullName") String fullName, @RequestParam("email") String email, @RequestParam("password") String password,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        userEmailValidator.validateIfUserEmailIsInUse(email);

        parametersRegexValidator.verifyIfUserNameIsValid(fullName);
        parametersRegexValidator.verifyIfUserIfEmailIsValid(email);
        parametersRegexValidator.verifyIfUserPasswordIsValid(password);
        RegisterUserRequest request = RegisterUserRequest.builder().password(password).email(email).fullName(fullName).build();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userAccountService.include(request, file);
    }

    @PutMapping("/edit")
    public ResponseMessage editUser(@Valid @RequestBody RegisterUserRequest request) {
        parametersRegexValidator.verifyIfUserNameIsValid(request.getFullName());
        parametersRegexValidator.verifyIfUserIfEmailIsValid(request.getEmail());
        parametersRegexValidator.verifyIfUserPasswordIsValid(request.getPassword());

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return updateUserService.editUser(request);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseMessage deleteUser(@PathVariable Long userId) {
        return deleteUserService.deleteUser(userId);
    }
}
