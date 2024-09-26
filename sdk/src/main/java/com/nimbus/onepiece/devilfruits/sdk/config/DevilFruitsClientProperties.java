package com.nimbus.onepiece.devilfruits.sdk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "onepiece.devilfruits.sdk.client")
@Getter
@Setter
public class DevilFruitsClientProperties {

    private String baseUrl = "http://localhost:8080";
}
