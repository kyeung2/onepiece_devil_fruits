package com.nimbus.onepiece.devilfruits.persistence;

import com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits;
import com.nimbus.onepiece.devilfruits.persistence.records.DevilFruitRecord;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integration")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DevilFruitRepositoryIntegrationTest {

    @Autowired
    DevilFruitRepository objectUnderTest;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Test
    @Order(1)
    void contextLoads() {
        assertTrue(postgres.isRunning());
    }

    @Test
    @Order(2)
    void findById() {
        //given
        String code = StrawHatDevilFruits.GOMU_GOMU_NO_MI.code();
        //when
        Optional<DevilFruitRecord> actual = objectUnderTest.findByCode(code);
        //then
        assertTrue(actual.isPresent());
        assertEquals(code, actual.get().code());
        assertEquals("Gomu Gomu no Mi", actual.get().name());
    }

    @Test
    @Order(3)
    void findAll() {
        //when
        Collection<DevilFruitRecord> actual = objectUnderTest.findAll();
        //then
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }

}