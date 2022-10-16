package br.com.cwi.crescer.api.service.finders;

import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountFinderByNickname {
    @Autowired
    private UserAccountRepository userAccountRepository;
    public UserAccount findByNicknameWithException(String nickname) {
        return userAccountRepository.findByNickname(nickname);
    }

}
