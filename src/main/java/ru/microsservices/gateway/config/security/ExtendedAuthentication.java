package ru.microsservices.gateway.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.microservices.role_service.PermissionModel;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class ExtendedAuthentication implements Authentication {

    private Long userId;
    private String email;
    private List<PermissionModel> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(
                                permission.getPrefix().name()
                                        +
                                        "_"
                                        +
                                        permission.getKey().name()
                        )
                ).toList();
    }

    @Override
    public Object getCredentials() {
        return userId;
    }

    @Override
    public Object getDetails() {
        return userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

    @Override
    public String getName() {
        return email;
    }
}
