package ru.microsservices.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class InstanceConfig {
    @Value("${instance.key}")
    private String key;
    @Value("${instance.id}")
    private String id;
}
