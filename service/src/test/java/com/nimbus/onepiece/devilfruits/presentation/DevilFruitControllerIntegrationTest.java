package com.nimbus.onepiece.devilfruits.presentation;

import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.domain.DevilFruitType;
import com.nimbus.onepiece.devilfruits.service.DevilFruitService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits.GOMU_GOMU_NO_MI;
import static org.mockito.Mockito.when;


@WebMvcTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DevilFruitControllerIntegrationTest {

    @MockBean
    DevilFruitService service;
    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void getDevilFruit() {
        // given
        when(service.getDevilFruit("DF0001")).thenReturn(Optional.of(
                DevilFruit.builder()
                        .code(GOMU_GOMU_NO_MI.code())
                        .name(GOMU_GOMU_NO_MI.name())
                        .type(DevilFruitType.valueOf(GOMU_GOMU_NO_MI.type().name()))
                        .ability(GOMU_GOMU_NO_MI.ability())
                        .build()
        ));
        //when
        webTestClient.get()
                .uri("/devilfruits/DF0001")
                .exchange()
                .expectStatus().isOk()
                //then
                .expectBody()
                .jsonPath("$.code").isEqualTo(GOMU_GOMU_NO_MI.code())
                .jsonPath("$.name").isEqualTo(GOMU_GOMU_NO_MI.name())
                .jsonPath("$.type").isEqualTo(GOMU_GOMU_NO_MI.type().name())
                .jsonPath("$.ability").isEqualTo(GOMU_GOMU_NO_MI.ability());
    }

    @Test
    @Order(2)
    void unexpectedException() {
        // given
        when(service.getDevilFruit("DF0001"))
                .thenThrow(new RuntimeException("Something unexpected happened"));
        //when
        webTestClient.get()
                .uri("/devilfruits/DF0001")
                .exchange()
                .expectStatus().is5xxServerError()
                //then
                .expectBody()
                .jsonPath("$.code").isEqualTo("DFE001")
                .jsonPath("$.message").isEqualTo("Something unexpected happened")
        ;
    }
}