package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserCompleteResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetDetailedUserService {

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private UserMapper userMapper;

    public UserCompleteResponse getDetailedUser(Long userId) {

        UserAccount user = findUserWithThrow.findByIdAndActiveWithException(userId, true);

        return userMapper.toResponseWithCoursesAndTeams(user);
    }
}
