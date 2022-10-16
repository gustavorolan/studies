package br.com.cwi.crescer.api.security.service;

import br.com.cwi.crescer.api.security.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoadUserInfoService {

    public UserAccount getUserFromJwt(Jwt jwt) {
        return UserAccount.builder()
                .email(jwt.getClaimAsString("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"))
                .registration(jwt.getClaimAsString("matricula"))
                .identifier(jwt.getClaimAsString("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier"))
                .fullName(jwt.getClaimAsString("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name"))
                .build();
    }

}