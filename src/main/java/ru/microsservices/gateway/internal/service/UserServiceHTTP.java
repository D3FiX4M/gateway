package ru.microsservices.gateway.internal.service;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.microsservices.gateway.external.service.UserService;
import ru.microsservices.gateway.internal.DTO.UserDTO;
import ru.microsservices.gateway.internal.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceHTTP {



    private final UserMapper mapper;
    private final UserService userService;

    public List<UserDTO> getAll() {
        return mapper.toDTOs(
                userService.defaultChannelBlockingStub()
                        .getAllUser(
                                Empty
                                        .newBuilder()
                                        .build()
                        )
        );
    }
}
