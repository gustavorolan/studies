package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.ChangeUserRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeUserService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public PutAndPostResponse change(ChangeUserRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();
        if (!request.getUserName().isBlank()) user.setUserName(request.getUserName());
        if (!request.getProfileImg().isBlank()) user.setProfileImg(request.getProfileImg());
        if (!request.getNickName().isBlank()) user.setNickname(request.getNickName());

        userAccountRepository.save(user);

        return PutAndPostResponse.builder()
                .response("Voce editou com sucesso")
                .build();
    }
}
