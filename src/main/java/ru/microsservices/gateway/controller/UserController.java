package ru.microsservices.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.microservices.user_service.UserIdRequest;
import ru.microservices.user_service.UserModel;
import ru.microservices.user_service.UserMultipleIdRequest;
import ru.microsservices.gateway.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/few")
    public ResponseEntity<List<UserModel>> getUsers(@RequestBody UserMultipleIdRequest request) {
        return ResponseEntity.ok(
                userService.getUsers(request)
        );
    }

    @GetMapping("/one}")
    public ResponseEntity<UserModel> getUser(@RequestBody UserIdRequest request) {
        return ResponseEntity.ok(
                userService.getUser(request)
        );
    }
}
