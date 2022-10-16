package br.com.cwi.crescer.api.security.service;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountAuthenticatedService {

    @Autowired
    private FindUserWithThrow findUserWithThrow;


    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccountSecurity userAccountSecurity = (UserAccountSecurity) authentication.getPrincipal();
        return userAccountSecurity.getId();
    }

    public UserAccount get() {
        return findUserWithThrow.findByIdWithException(getId());
    }
}
