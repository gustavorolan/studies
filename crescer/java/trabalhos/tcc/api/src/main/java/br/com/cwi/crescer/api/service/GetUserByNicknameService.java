package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByNicknameService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public UserAccountResponse get(String nickname) {
         UserAccount user = userAccountRepository.findByNickname(nickname);
         UserAccountResponse response = userResponseMapper.toResponse(user);
         return userResponseMapper.toResponse(user);

    }
}
