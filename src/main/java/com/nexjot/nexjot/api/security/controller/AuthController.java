package com.nexjot.nexjot.api.security.controller;

import com.nexjot.nexjot.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Redirects the user to the frontend upon successful authentication.
     * @return a URL redirect to the frontend for further actions.
     */
    @GetMapping("/oauth2/authorize/google")
    public String handleGoogleLogin() {
        return "redirect:http://localhost:5173/oauth2/redirect";
    }

    @GetMapping("/api/user")
    public String getUser() {
        return "username=" + userService.getCurrentAuthUser().getUsername();
    }
}
