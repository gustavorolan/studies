package br.com.cwi.crescer.api.security.service;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SearchUserAccountSecuritySerivce implements UserDetailsService {

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        UserAccount userAccount = findUserWithThrow.findByEmailWithException(email);
        return new UserAccountSecurity(userAccount);
    }
}