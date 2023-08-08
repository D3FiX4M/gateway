package ru.microsservices.gateway.external.service;

import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.Setter;
import org.lognet.springboot.grpc.security.AuthClientInterceptor;
import org.lognet.springboot.grpc.security.AuthHeader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.ByteBuffer;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "grpc.client.user-service")
public class UserServiceConfig {
    private String address;
    private Integer port;

    @Value("${token}")
    private String token;

    private ByteBuffer getMyToken() {
        return ByteBuffer.wrap(token.getBytes());
    }

    public interface Qualifiers {
        interface Channel {
            String DEFAULT = "UserServiceConfig#DEFAULT_CHANNEL";
        }
    }

    @Bean
    @Qualifier(Qualifiers.Channel.DEFAULT)
    public Channel settingServiceDefaultChannel() {

        return ClientInterceptors.intercept(
                ManagedChannelBuilder.forAddress(
                        this.address,
                        this.port
                ).usePlaintext().build(),
                new AuthClientInterceptor(
                        AuthHeader.builder()
                                .bearer()
                                .binaryFormat(false)
                                .tokenSupplier(this::getMyToken)
                                .build()
                )
        );
    }
}
