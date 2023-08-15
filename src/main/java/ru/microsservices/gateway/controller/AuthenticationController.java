package ru.microsservices.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microservices.authentication_service.CreateTokenRequest;
import ru.microservices.authentication_service.RefreshTokenRequest;
import ru.microservices.authentication_service.TokenResponse;
import ru.microservices.user_service.CreateUserRequest;
import ru.microsservices.gateway.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/sign/in")
    public ResponseEntity<TokenResponse> signIn(@RequestBody CreateTokenRequest request) {
        return ResponseEntity.ok(service.signIn(request));
    }

    @PostMapping("/sign/up")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserRequest request) {
        service.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refresh(request));
    }
}
