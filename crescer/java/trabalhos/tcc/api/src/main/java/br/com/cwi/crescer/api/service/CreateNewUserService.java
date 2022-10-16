package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CreateNewUserRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.validator.VerifyIfEmailRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateNewUserService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerifyIfEmailRegexValidator verifyIfEmailRegexValidator;

    public PutAndPostResponse create(CreateNewUserRequest request) {
        verifyIfEmailRegexValidator.verifyIfUserIfEmailIsValid(request.getEmail());
        String passwordEncoded = passwordEncoder.encode(request.getPassword());
        UserAccount userAccount = UserAccount.builder()
                .userName(request.getUserName())
                .nickname(request.getNickName())
                .email(request.getEmail())
                .password(passwordEncoded)
                .profileImg(request.getProfileImg())
                .postList(new ArrayList<>())
                .build();

        userAccountRepository.save(userAccount);

        return PutAndPostResponse.builder()
                .response("Voce criou um novo usuario com o nome" + request.getUserName())
                .build();
    }
}