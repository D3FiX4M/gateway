package ru.microsservices.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.microservices.user_service.CreateUserRequest;
import ru.microservices.user_service.UserIdRequest;
import ru.microservices.user_service.UserModel;
import ru.microservices.user_service.UserMultipleIdRequest;
import ru.microsservices.gateway.grpc.UserGrpc;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGrpc userGrpc;


    public UserModel getUser(UserIdRequest request) {
        return userGrpc.defaultChannelBlockingStub()
                .getUser(request)
                .getUser();
    }

    public List<UserModel> getUsers(UserMultipleIdRequest request) {
        return
                userGrpc.defaultChannelBlockingStub()
                        .getUsers(request)
                        .getUsersList();
    }

    public UserModel create(CreateUserRequest request) {
        return userGrpc.defaultChannelBlockingStub()
                .create(request)
                .getUser();
    }

}
