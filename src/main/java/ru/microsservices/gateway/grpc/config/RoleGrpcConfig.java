package ru.microsservices.gateway.grpc.config;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "grpc.client.role-service")
public class RoleGrpcConfig {
    private String address;
    private Integer port;

    @Bean
    public Channel defaultChannel() {
        return ManagedChannelBuilder.forAddress(
                this.address,
                this.port
        ).usePlaintext().build();

    }
}
