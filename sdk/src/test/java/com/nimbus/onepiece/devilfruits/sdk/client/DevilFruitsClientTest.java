package com.nimbus.onepiece.devilfruits.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits;
import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;
import com.nimbus.onepiece.devilfruits.sdk.errors.DevilFruitsClientException;
import com.nimbus.onepiece.devilfruits.sdk.errors.DevilFruitsServerException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("integration")
@AutoConfigureWireMock(port = 8081)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DevilFruitsClientTest {

    @Autowired
    DevilFruitsClient objectUnderTest;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    void getDevilFruit_success() {
        //given
        DevilFruitDto fruit = StrawHatDevilFruits.GOMU_GOMU_NO_MI;
        stubGetDevilFruitResponse(fruit);
        //when
        Mono<DevilFruitDto> actual = objectUnderTest.getDevilFruit(fruit.code());
        //then
        StepVerifier.create(actual)
                .expectNextMatches(devilFruit -> devilFruit.code().equals(fruit.code()))
                .expectComplete()
                .verify();
    }

    @Test
    @Order(2)
    void getDevilFruit_notfound() {
        //given
        DevilFruitDto fruit = StrawHatDevilFruits.GOMU_GOMU_NO_MI;
        ErrorDto expectedErrorDto = ErrorDto.builder()
                //TODO once the error contracts mature improve this
                .code("some_notfound_error")
                .message("could not find fruit")
                .build();
        stubGetDevilFruitResponse_error(fruit, expectedErrorDto, 404);
        //when
        Mono<DevilFruitDto> actual = objectUnderTest.getDevilFruit(fruit.code());
        //then
        StepVerifier.create(actual)
                .expectErrorSatisfies(error -> {
                    if (error instanceof DevilFruitsClientException clientEx) {
                        assertEquals(expectedErrorDto, clientEx.getError());
                    } else {
                        fail("Expected DevilFruitsClientException but was " + error.getClass().getSimpleName());
                    }
                })
                .verify();
    }

    @Test
    @Order(3)
    void getAllDevilFruits_success() {
        //given
        stubGetAllDevilFruitResponse(StrawHatDevilFruits.ALL_STRAW_HATS);
        //when
        Flux<DevilFruitDto> actual = objectUnderTest.getAllDevilFruits();
        //then
        StepVerifier.create(actual)
                .expectNextCount(StrawHatDevilFruits.ALL_STRAW_HATS.size())
                .expectComplete()
                .verify();
    }

    @Test
    @Order(4)
    void getAllDevilFruits_internalServerError() {
        //given
        ErrorDto expectedErrorDto = ErrorDto.builder()
                //TODO once the error contracts mature improve this
                .code("some_internal_error")
                .message("A null pointer maybe")
                .build();
        stubGetAllDevilFruitResponse_error(expectedErrorDto, 500);
        //when
        Flux<DevilFruitDto> actual = objectUnderTest.getAllDevilFruits();
        //then
        StepVerifier.create(actual)
                .expectErrorSatisfies(error -> {
                    if (error instanceof DevilFruitsServerException serverEx) {
                        assertEquals(expectedErrorDto, serverEx.getError());
                    } else {
                        fail("Expected DevilFruitsServerException but was " + error.getClass().getSimpleName());
                    }
                })
                .verify();
    }

    @SneakyThrows
    private void stubGetDevilFruitResponse(DevilFruitDto fruit) {
        stubFor(get(urlEqualTo("/devilfruits/%s".formatted(fruit.code())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(fruit))
                        .withStatus(200)));
    }

    @SneakyThrows
    private void stubGetDevilFruitResponse_error(DevilFruitDto fruit, ErrorDto error, int status) {
        stubFor(get(urlEqualTo("/devilfruits/%s".formatted(fruit.code())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(error))
                        .withStatus(status)));
    }

    @SneakyThrows
    private void stubGetAllDevilFruitResponse(Collection<DevilFruitDto> fruits) {
        stubFor(get(urlEqualTo("/devilfruits"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(fruits))
                        .withStatus(200)));
    }

    @SneakyThrows
    private void stubGetAllDevilFruitResponse_error(ErrorDto error, int status) {
        stubFor(get(urlEqualTo("/devilfruits"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(error))
                        .withStatus(status)));
    }
}