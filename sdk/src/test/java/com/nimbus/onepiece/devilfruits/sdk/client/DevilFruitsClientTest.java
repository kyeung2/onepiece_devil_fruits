package com.nimbus.onepiece.devilfruits.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits;
import com.nimbus.onepiece.devilfruits.sdk.client.DevilFruitsClient;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("integration")
@AutoConfigureWireMock(port = 8081)
class DevilFruitsClientTest {

    @Autowired
    DevilFruitsClient objectUnderTest;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getDevilFruit() {
        //given
        DevilFruitDto fruit = StrawHatDevilFruits.GOMU_GOMU_NO_MI;
        stubGetDevilFruitResponse(fruit);
        //when
        DevilFruitDto actual = objectUnderTest.getDevilFruit(fruit.code()).block();
        //then
        assertNotNull(actual);
        assertEquals(fruit, actual);
    }

    @Test
    void getAllDevilFruits() {
        //given
        stubGetAllDevilFruitResponse(StrawHatDevilFruits.ALL_STRAW_HATS);
        //when
        Collection<DevilFruitDto> actual = objectUnderTest.getAllDevilFruits().collectList().block();
        //then
        assertNotNull(actual);
        assertEquals(StrawHatDevilFruits.ALL_STRAW_HATS.size(), actual.size());
    }

    //TODO add test for exception

    @SneakyThrows
    private void stubGetDevilFruitResponse(DevilFruitDto fruit) {
        stubFor(get(urlEqualTo("/devilfruits/%s".formatted(fruit.code())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(fruit))
                        .withStatus(200)));
    }

    @SneakyThrows
    private void stubGetAllDevilFruitResponse(Collection<DevilFruitDto> fruits) {
        stubFor(get(urlEqualTo("/devilfruits"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(fruits))
                        .withStatus(200)));
    }
}