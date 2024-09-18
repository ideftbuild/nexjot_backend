package com.nexjot.nexjot.api.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    /**
     * Redirects the user to the frontend upon successful authentication.
     * @return a URL redirect to the frontend for further actions.
     */
    @GetMapping("/oauth2/authorize/google")
    public String handleGoogleLogin() {
        return "redirect:http://localhost:5173/oauth2/redirect";
    }
}
