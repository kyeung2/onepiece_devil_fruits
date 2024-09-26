package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.sdk.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.sdk.dto.StrawHatDevilFruits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integration")
class DefaultBlockingDevilFruitsClientTest {

    @Autowired
    BlockingDevilFruitsClient objectUnderTest;

    @Test
    void getDevilFruit() {
        //TODO set up a wiremock server to simulate the devil fruit service running.
        //given
        String code = StrawHatDevilFruits.GOMU_GOMU_NO_MI.code();
        //when
        Optional<DevilFruitDto> actual = objectUnderTest.getDevilFruit(code);
        //then
        assertTrue(actual.isPresent());
    }

}