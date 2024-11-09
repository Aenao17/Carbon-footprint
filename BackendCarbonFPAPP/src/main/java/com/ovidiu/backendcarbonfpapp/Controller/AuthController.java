package com.ovidiu.backendcarbonfpapp.Controller;

import com.ovidiu.backendcarbonfpapp.DTO.LoginRequest;
import com.ovidiu.backendcarbonfpapp.DTO.RegisterRequest;
import com.ovidiu.backendcarbonfpapp.Service.UserService;
import com.ovidiu.backendcarbonfpapp.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint pentru inregistrare
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
        userService.register(registerRequest);
        return "User registered successfully";
    }

    // Endpoint pentru autentificare
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
        String token = userService.login(loginRequest);
        return "Bearer " + token;
    }

    // Endpoint pentru logout
    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Elimină prefixul "Bearer "
            jwtUtil.invalidateToken(token);
            return "User logged out successfully";
        } else {
            throw new IllegalArgumentException("Invalid token format");
        }
    }
}
