package com.nexjot.nexjot.api.security.model;

import com.nexjot.nexjot.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
@AllArgsConstructor
public class AuthUser implements OAuth2User{

    private User user;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    public String getEmail() {
        return attributes.get("email").toString();
    }
}
