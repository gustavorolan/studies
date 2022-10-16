package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.RegisteredCoursesResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.validator.ValidateIfActiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetRegisteredCoursesService {
    @Autowired
    private UserAccountAuthenticatedService accountAuthenticatedService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValidateIfActiveValidator validateIfActiveValidator;

    public RegisteredCoursesResponse getCourses() {
        UserAccount user = accountAuthenticatedService.get();

        validateIfActiveValidator.validateIfActive(user.getActive());

        return userMapper.toResponseWithCourses(user);
    }
}
