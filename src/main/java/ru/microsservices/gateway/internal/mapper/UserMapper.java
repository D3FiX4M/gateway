package ru.microsservices.gateway.internal.mapper;

import org.springframework.stereotype.Component;
import ru.microservices.proto.UserListResponse;
import ru.microservices.proto.UserModel;
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


    public List<UserDTO> toDTOs(UserListResponse userListResponse) {
        return userListResponse
                .getUsersList()
                .stream()
                .map(this::toDTO)
                .toList();
    }

}
