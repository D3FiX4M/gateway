package ru.microsservices.gateway.grpc;

import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.microservices.authentication_service.AuthenticationServiceGrpc;

@Component
@RequiredArgsConstructor
public class AuthenticationGrpc {

    private final Channel channel;

    public AuthenticationServiceGrpc.AuthenticationServiceBlockingStub defaultChannelBlockingStub() {
        return AuthenticationServiceGrpc.newBlockingStub(channel);
    }
}
