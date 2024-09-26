package com.nimbus.onepiece.devilfruits.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbus.onepiece.devilfruits.sdk.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.sdk.dto.StrawHatDevilFruits;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("integration")
@AutoConfigureWireMock(port = 8081)
class DefaultBlockingDevilFruitsClientTest {

    @Autowired
    BlockingDevilFruitsClient objectUnderTest;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getDevilFruit() {
        //given
        DevilFruitDto fruit = StrawHatDevilFruits.GOMU_GOMU_NO_MI;
        stubGetDevilFruitResponse(fruit);
        //when
        Optional<DevilFruitDto> actual = objectUnderTest.getDevilFruit(fruit.code());
        //then
        assertTrue(actual.isPresent());
        assertEquals(fruit, actual.get());
    }

    //TODO add test for exception
    //TODO add test for getAll

    @SneakyThrows
    private void stubGetDevilFruitResponse(DevilFruitDto fruit) {
        stubFor(get(urlEqualTo("/devilfruits/%s".formatted(fruit.code())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(fruit))
                        .withStatus(200)));
    }
}