package ru.microsservices.gateway.external.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microsservices.gateway.internal.DTO.UserDTO;
import ru.microsservices.gateway.internal.service.UserServiceHTTP;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserServiceHTTP userServiceHTTP;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(
                userServiceHTTP.getAll()
        );
    }
}
