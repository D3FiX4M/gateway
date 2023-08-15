package ru.microsservices.gateway.grpc;

import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.microservices.user_service.UserServiceGrpc;


@Component
@RequiredArgsConstructor
public class UserGrpc {

    private final Channel channel;

    public UserServiceGrpc.UserServiceBlockingStub defaultChannelBlockingStub() {
        return UserServiceGrpc.newBlockingStub(channel);
    }
}
