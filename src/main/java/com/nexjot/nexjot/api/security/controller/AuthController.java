package com.nexjot.nexjot.api.security.controller;

import com.nexjot.nexjot.api.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Redirects the user to the frontend upon successful authentication.
     * @return a URL redirect to the frontend for further actions.
     */
    @GetMapping("/oauth2/authorize/google")
    public String handleGoogleLogin() {
        System.out.println("accessing the endpoint, redirecting the user");
        return "redirect:http://localhost:5173/oauth2/redirect";
    }

    @GetMapping("/auth/status")
    public ResponseEntity<String> apiStatus() {
        String message = "User is not authenticated";
        if (authService.isAuthenticated()) message = "User is authenticated";
        return ResponseEntity.ok(message);
    }
}
