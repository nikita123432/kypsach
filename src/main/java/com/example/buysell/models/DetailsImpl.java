package com.example.buysell.models;

import  lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class DetailsImpl implements UserDetails {
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
    public static DetailsImpl build(User user){
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
        return new DetailsImpl(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorityList
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
