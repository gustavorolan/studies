package br.com.cwi.crescer.api.security.service;

import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

public class UserAccountSecurity implements UserDetails {

    private Long id;
    private String email;

    @Column(unique = true)
    private String password;
    private List<SimpleGrantedAuthority> permissions;

    public UserAccountSecurity(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.email = userAccount.getEmail();
        this.password = userAccount.getPassword();
        this.permissions = convertPermissions(userAccount);
    }

    private List<SimpleGrantedAuthority> convertPermissions(UserAccount userAccount) {
        return userAccount.getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority("ROLE_" + permissao.getName()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
