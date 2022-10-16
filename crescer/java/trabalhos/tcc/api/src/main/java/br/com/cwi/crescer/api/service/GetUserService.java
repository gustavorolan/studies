package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public  UserAccountResponse get() {
        UserAccount user = findUserAuthenticatedService.getUser();
        return  userResponseMapper.toResponse(user);
    }
}
