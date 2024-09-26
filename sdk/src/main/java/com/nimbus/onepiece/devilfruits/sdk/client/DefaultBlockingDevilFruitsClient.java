package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.sdk.dto.DevilFruitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DefaultBlockingDevilFruitsClient implements BlockingDevilFruitsClient {

    private final WebClient webClient;

    @Override
    public Optional<DevilFruitDto> getDevilFruit(String code) {
        Mono<DevilFruitDto> mono = webClient.get()
                .uri("/devilfruits/{code}", code)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(response -> {
                    //TODO deal with 404
                    //TODO deal with errors
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(DevilFruitDto.class);
                    }
                    return Mono.empty();
                });

        return mono.blockOptional();
    }

    @Override
    public Collection<DevilFruitDto> getAllDevilFruits() {
        return List.of();
    }
}
