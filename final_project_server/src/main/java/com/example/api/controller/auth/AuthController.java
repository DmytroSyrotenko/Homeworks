package com.example.api.controller.auth;

import com.example.api.dto.request.auth.RegisterDto;
import com.example.api.dto.response.auth.AuthDto;
import com.example.config.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authenticationService.register(dto));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authenticationService.login(dto));
    }
}
