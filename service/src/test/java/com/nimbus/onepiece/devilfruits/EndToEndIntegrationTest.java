package com.nimbus.onepiece.devilfruits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.nimbus.onepiece.devilfruits.sdk.dto.StrawHatDevilFruits.GOMU_GOMU_NO_MI;


/// ## End to End Integration
/// Every layer of the application is used with one real external request in and
/// one real response asserted on.
///
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
@Testcontainers
@AutoConfigureWebTestClient
class EndToEndIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    WebTestClient webTestClient;

    @Test
    void fullFlow() {
        // when
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
