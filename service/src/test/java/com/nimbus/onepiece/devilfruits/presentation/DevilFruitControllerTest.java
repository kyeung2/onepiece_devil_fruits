package com.nimbus.onepiece.devilfruits.presentation;

import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.domain.DevilFruitType;
import com.nimbus.onepiece.devilfruits.service.DevilFruitService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits.GOMU_GOMU_NO_MI;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class DevilFruitControllerTest {

    @MockBean
    DevilFruitService service;
    @Autowired
    WebTestClient webTestClient;

    @Test
    void testGetDevilFruit() {
        // given
        Mockito.when(service.getDevilFruit("DF0001")).thenReturn(Optional.of(
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
}