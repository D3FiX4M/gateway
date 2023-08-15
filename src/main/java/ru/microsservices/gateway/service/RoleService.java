package ru.microsservices.gateway.service;

import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.microservices.role_service.*;
import ru.microsservices.gateway.grpc.RoleGrpc;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleService {
    private final RoleGrpc grpc;

    public void createRole(CreateRoleRequest request) {
        grpc.defaultChannelBlockingStub()
                .createRole(request);
    }

    public RoleModel getRole(RoleIdRequest request) {
        return grpc.defaultChannelBlockingStub()
                .getRole(request)
                .getRole();
    }

    public List<RoleModel> getRoles(RoleMultipleIdRequest request) {
        return grpc.defaultChannelBlockingStub()
                .getRoles(request)
                .getRolesList();
    }

    public void updateRole(UpdateRoleRequest request) {
        grpc.defaultChannelBlockingStub()
                .updateRole(request);
    }

    public void deleteRole(RoleMultipleIdRequest request) {
        grpc.defaultChannelBlockingStub()
                .deleteRoles(request);
    }

    public List<PermissionModel> getPermissionByPrefix(PermissionByPrefixRequest request) {
        return grpc.defaultChannelBlockingStub()
                .getPermissionByPrefix(request)
                .getPermissionsList();
    }

    public List<PermissionModel> getPermissions() {
        return grpc.defaultChannelBlockingStub()
                .getPermissions(Empty.newBuilder().build())
                .getPermissionsList();
    }
}
