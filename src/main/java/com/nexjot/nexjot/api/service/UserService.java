package com.nexjot.nexjot.api.service;

import com.nexjot.nexjot.api.model.User;
import com.nexjot.nexjot.api.security.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    /**
     * Get the current authenticated user
     * @return the current authenticated User object
     */
    public User getCurrentAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return authUser.getUser();
    }
}
