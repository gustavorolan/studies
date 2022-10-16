package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.validator.ValidateIfActiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserAuthenticated {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValidateIfActiveValidator validateIfActiveValidator;

    public UserResponse get() {
        UserAccount user = userAccountAuthenticatedService.get();

        validateIfActiveValidator.validateIfActive(user.getActive());

        return userMapper.toResponse(user);
    }}