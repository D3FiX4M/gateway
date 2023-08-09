package ru.microsservices.gateway.internal.mapper;

import org.springframework.stereotype.Component;
import ru.microservices.proto.UserModel;
import ru.microservices.proto.UserResponse;
import ru.microservices.proto.UsersResponse;
import ru.microsservices.gateway.internal.DTO.UserDTO;

import java.util.List;

@Component
public class UserMapper {

    private UserDTO toDTO(UserModel userModel) {
        return new UserDTO(
                userModel.getId(),
                userModel.getUsername()
        );
    }

    public UserDTO toDTO(UserResponse userResponse) {
        return toDTO(
                userResponse.getUser()
        );
    }

    public List<UserDTO> toDTOs(UsersResponse usersResponse) {
        return usersResponse
                .getUsersList()
                .stream()
                .map(this::toDTO)
                .toList();
    }

}
