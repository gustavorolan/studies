package br.com.cwi.crescer.api.service.security;

import br.com.cwi.crescer.api.model.UserAccount;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {
    private Long id;
    private String password;
    private String email;
    private List<SimpleGrantedAuthority> permissions;

    public SecurityUser (UserAccount user){
        this.id= user.getUserId();
        this.email=user.getEmail();
        this.password= user.getPassword();
        this.permissions = user.getPermissionList()
                .stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_"+ permission.getPermissionName()))
                .collect(Collectors.toList());
    }

    public Long getId (){
        return this.id;
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
