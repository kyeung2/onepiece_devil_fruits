package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;
import com.nimbus.onepiece.devilfruits.sdk.errors.DevilFruitsClientException;
import com.nimbus.onepiece.devilfruits.sdk.errors.DevilFruitsGenericException;
import com.nimbus.onepiece.devilfruits.sdk.errors.DevilFruitsServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DefaultDevilFruitsClient implements DevilFruitsClient {

    private final WebClient webClient;

    @Override
    public Mono<DevilFruitDto> getDevilFruit(String code) {
        return webClient.get()
                .uri("/devilfruits/{code}", code)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(rs -> {
                    if (rs.statusCode().is2xxSuccessful()) {
                        return rs.bodyToMono(DevilFruitDto.class);
                    } else if (rs.statusCode().is4xxClientError()) {
                        return rs.bodyToMono(ErrorDto.class).flatMap(errorDto -> Mono.error(new DevilFruitsClientException(errorDto, null)));
                    } else if (rs.statusCode().is5xxServerError()) {
                        return rs.bodyToMono(ErrorDto.class).flatMap(errorDto -> Mono.error(new DevilFruitsServerException(errorDto, null)));
                    } else {
                        return rs.createException().flatMap(cause -> Mono.error(new DevilFruitsGenericException(null, cause)));
                    }
                })
                // e.g. network, timeout, response processing exceptions inside the exchangeToMono block
                .onErrorResume(
                        ex -> !(ex instanceof DevilFruitsGenericException),
                        ex -> Mono.error(new DevilFruitsGenericException(null, ex)));
    }

    @Override
    public Flux<DevilFruitDto> getAllDevilFruits() {
        return webClient.get()
                .uri("/devilfruits")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToFlux(rs -> {
                    if (rs.statusCode().is2xxSuccessful()) {
                        return rs.bodyToFlux(DevilFruitDto.class);
                    } else if (rs.statusCode().is4xxClientError()) {
                        return rs.bodyToMono(ErrorDto.class).flatMapMany(errorDto -> Flux.error(new DevilFruitsClientException(errorDto, null)));
                    } else if (rs.statusCode().is5xxServerError()) {
                        return rs.bodyToMono(ErrorDto.class).flatMapMany(errorDto -> Flux.error(new DevilFruitsServerException(errorDto, null)));
                    } else {
                        return rs.createException().flatMapMany(cause -> Flux.error(new DevilFruitsGenericException(null, cause)));
                    }
                })
                // e.g. network, timeout, response processing exceptions inside the exchangeToMono block
                .onErrorResume(
                        ex -> !(ex instanceof DevilFruitsGenericException),
                        ex -> Flux.error(new DevilFruitsGenericException(null, ex)));
    }

}
