package ru.microsservices.gateway.grpc;

import io.grpc.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.microservices.role_service.RoleServiceGrpc;

@Component
@RequiredArgsConstructor
public class RoleGrpc {

    private final Channel channel;

    public RoleServiceGrpc.RoleServiceBlockingStub defaultChannelBlockingStub() {
        return RoleServiceGrpc.newBlockingStub(channel);
    }
}
