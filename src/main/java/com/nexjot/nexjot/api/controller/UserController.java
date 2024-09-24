//package com.nexjot.nexjot.api.controller;
//
//import com.nexjot.nexjot.api.dto.user.LoginDTO;
//import com.nexjot.nexjot.api.dto.user.SignUpDTO;
//import com.nexjot.nexjot.api.dto.user.UserDTO;
//import com.nexjot.nexjot.api.security.model.AuthUser;
//import com.nexjot.nexjot.api.service.UserService;
//import com.sun.net.httpserver.HttpContext;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.apache.coyote.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class UserController {
//
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    @PostMapping("/signup")
//    public ResponseEntity<UserDTO> registerUser(
//            @Valid @RequestBody SignUpDTO signUpDTO) {
//        return ResponseEntity.ok(userService.signUp(signUpDTO));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserDTO> authenticateUser(
//            @Valid @RequestBody LoginDTO loginDTO) {
//        return ResponseEntity.ok(userService.logIn(loginDTO));
//    }
//}
