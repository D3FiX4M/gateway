package ru.microsservices.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.microservices.role_service.*;
import ru.microsservices.gateway.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping("/one")
    public ResponseEntity<RoleModel> getRole(@RequestBody RoleIdRequest request) {
        return ResponseEntity.ok(service.getRole(request));
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> getRoles(@RequestBody RoleMultipleIdRequest request) {
        return ResponseEntity.ok(service.getRoles(request));
    }

    @PutMapping("/one")
    public ResponseEntity<Void> updateRole(@RequestBody UpdateRoleRequest request) {
        service.updateRole(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/one")
    public ResponseEntity<Void> deleteRole(@RequestBody RoleMultipleIdRequest request) {
        service.deleteRole(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/permission")
    public ResponseEntity<List<PermissionModel>> getPermissions() {
        return ResponseEntity.ok(service.getPermissions());
    }

    @GetMapping("/permission/prefix")
    public ResponseEntity<List<PermissionModel>> getPermissionByPrefix(@RequestBody PermissionByPrefixRequest request) {
        return ResponseEntity.ok(service.getPermissionByPrefix(request));
    }
}
