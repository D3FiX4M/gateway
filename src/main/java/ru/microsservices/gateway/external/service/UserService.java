package ru.microsservices.gateway.external.service;

import io.grpc.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.microservices.proto.UserServiceGrpc;

@Component
public class UserService {

    @Autowired
    @Qualifier(UserServiceConfig.Qualifiers.Channel.DEFAULT)
    private Channel defaultChannel;

    public UserServiceGrpc.UserServiceBlockingStub defaultChannelBlockingStub() {

        return UserServiceGrpc.newBlockingStub(defaultChannel);
    }
}
