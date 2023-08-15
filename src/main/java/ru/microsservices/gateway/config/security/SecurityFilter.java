package ru.microsservices.gateway.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.microservices.authentication_service.ValidateTokenRequest;
import ru.microservices.authentication_service.ValidateTokenResponse;
import ru.microservices.role_service.PermissionModel;
import ru.microservices.role_service.RoleIdRequest;
import ru.microservices.user_service.UserIdRequest;
import ru.microservices.user_service.UserModel;
import ru.microsservices.gateway.service.AuthenticationService;
import ru.microsservices.gateway.service.RoleService;
import ru.microsservices.gateway.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;
    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {


            ValidateTokenResponse validateToken = authenticationService
                    .validate(
                            ValidateTokenRequest.newBuilder()
                                    .setAccessToken(
                                            authorizationHeader.substring(
                                                    TOKEN_PREFIX.length()
                                            )
                                    )
                                    .build()
                    );

            if (validateToken.getIsExpired()) {
                filterChain.doFilter(request, response);
                //TODO
                throw new RuntimeException();
            }

            if (LocalDateTime.parse(
                            validateToken.getExpiredAt()
                    )
                    .isBefore(LocalDateTime.now()
                    )
            ) {
                filterChain.doFilter(request, response);
                //TODO
                throw new RuntimeException();
            }


            UserModel user = userService
                    .getUser(
                            UserIdRequest.newBuilder()
                                    .setId(validateToken.getUserId())
                                    .build()
                    );

            List<PermissionModel> permissions = roleService
                    .getRole(
                            RoleIdRequest.newBuilder()
                                    .setId(user.getRoleId())
                                    .build()
                    ).getPermissionsList();

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            new ExtendedAuthentication(
                                    validateToken.getUserId(),
                                    user.getEmail(),
                                    permissions
                            )
                    );

        }

        filterChain.doFilter(request, response);
    }
}
