package com.nimbus.onepiece.devilfruits.sdk.config;

import com.nimbus.onepiece.devilfruits.sdk.client.BlockingDevilFruitsClient;
import com.nimbus.onepiece.devilfruits.sdk.client.DefaultBlockingDevilFruitsClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(name = "onepiece.devilfruits.sdk.client.enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(DevilFruitsClientProperties.class)
public class DevilFruitsClientAutoConfiguration {

    @Bean(name = "devilFruitsWebClient")
    public WebClient devilFruitsWebClient(DevilFruitsClientProperties props) {
        return WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public BlockingDevilFruitsClient blockingDevilFruitsClient(@Qualifier("devilFruitsWebClient") WebClient webClient) {
        return new DefaultBlockingDevilFruitsClient(webClient);
    }

}
