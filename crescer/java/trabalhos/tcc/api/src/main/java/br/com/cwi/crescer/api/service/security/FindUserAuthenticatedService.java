package br.com.cwi.crescer.api.service.security;

import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FindUserAuthenticatedService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return  userAccountRepository.findById(securityUser.getId()).get();
    }
}
