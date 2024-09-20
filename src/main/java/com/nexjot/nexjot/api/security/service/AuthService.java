package com.nexjot.nexjot.api.security.service;

import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.security.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    /**
     * Get the authentication object from the current security context
     * @return the current authentication object
     */
    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Get the principal object from the current authentication context
     * @return the principal object
     */
    public AuthUser getAuthPrincipal() {
        return (AuthUser) getAuth().getPrincipal();
    }

    /**
     * Get the current authenticated user
     * @return the current authenticated User object
     */
    public User getAuthUser() {
        return getAuthPrincipal().getUser();
    }

    public boolean isAuthenticated() {
        Authentication authentication = getAuth();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof  String);
    }
}