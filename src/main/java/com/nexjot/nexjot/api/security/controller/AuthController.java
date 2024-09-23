package com.nexjot.nexjot.api.security.controller;

import com.nexjot.nexjot.api.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

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
    public String handleGoogleLogin(
            @RequestParam("redirect-uri") String encodedRedirectUri) throws UnsupportedEncodingException {
        String decodeUrl = URLDecoder.decode(encodedRedirectUri, StandardCharsets.UTF_8);
        return "redirect:" + decodeUrl;
    }

    @GetMapping("/auth/status")
    public ResponseEntity<String> apiStatus() {
        if (!authService.isAuthenticated())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        return ResponseEntity.ok("User is authenticated");
    }
}
