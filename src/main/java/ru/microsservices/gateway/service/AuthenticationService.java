package ru.microsservices.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.microservices.authentication_service.*;
import ru.microservices.user_service.CreateUserRequest;
import ru.microsservices.gateway.grpc.AuthenticationGrpc;
import ru.microsservices.gateway.grpc.UserGrpc;

@Component
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationGrpc authenticationGrpc;
    private final UserGrpc userGrpc;

    public TokenResponse signIn(CreateTokenRequest request) {
        return authenticationGrpc.defaultChannelBlockingStub()
                .createToken(request);
    }

    public void signUp(CreateUserRequest request) {
        userGrpc.defaultChannelBlockingStub()
                .create(request);
    }

    public ValidateTokenResponse validate(ValidateTokenRequest request) {
        return authenticationGrpc.defaultChannelBlockingStub()
                .validateToken(request);
    }

    public TokenResponse refresh(RefreshTokenRequest request) {
        return authenticationGrpc.defaultChannelBlockingStub()
                .refreshToken(request);
    }
}
