package com.nimbus.onepiece.devilfruits.sdk.config;

import com.nimbus.onepiece.devilfruits.sdk.client.DefaultDevilFruitsClient;
import com.nimbus.onepiece.devilfruits.sdk.client.DevilFruitsClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfiguration
@ConditionalOnProperty(name = "onepiece.devilfruits.sdk.client.enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(DevilFruitsClientProperties.class)
public class DevilFruitsClientAutoConfiguration {

    @Bean(name = "devilFruitsWebClient")
    public WebClient devilFruitsWebClient(DevilFruitsClientProperties props, WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(props.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public DevilFruitsClient devilFruitsClient(@Qualifier("devilFruitsWebClient") WebClient webClient) {
        return new DefaultDevilFruitsClient(webClient);
    }

}
