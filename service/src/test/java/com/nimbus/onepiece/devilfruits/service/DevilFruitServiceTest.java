package com.nimbus.onepiece.devilfruits.service;

import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.domain.DevilFruitType;
import com.nimbus.onepiece.devilfruits.persistence.DevilFruitRepository;
import com.nimbus.onepiece.devilfruits.persistence.records.DevilFruitRecord;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.nimbus.onepiece.devilfruits.interfaces.dto.StrawHatDevilFruits.GOMU_GOMU_NO_MI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DevilFruitServiceTest {

    @Mock
    DevilFruitRepository repository;
    @InjectMocks
    DevilFruitService objectUnderTest;

    @Test
    @Order(1)
    void getDevilFruit() {
        // given
        DevilFruitRecord record = getRecord();
        when(repository.findByCode(record.code())).thenReturn(Optional.of(record));
        // when
        Optional<DevilFruit> actual = objectUnderTest.getDevilFruit(record.code());
        // then
        assertTrue(actual.isPresent());
        assertMapping(record, actual.get());
    }

    @Test
    @Order(2)
    void getDevilFruit_notfound() {
        // given
        String unknownCode = "unknown";
        when(repository.findByCode(unknownCode)).thenReturn(Optional.empty());
        // when
        Optional<DevilFruit> actual = objectUnderTest.getDevilFruit(unknownCode);
        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    @Order(3)
    void getAllDevilFruits() {
        // given
        DevilFruitRecord record = getRecord();
        when(repository.findAll()).thenReturn(List.of(record, record, record));
        // when
        Collection<DevilFruit> actual = objectUnderTest.getAllDevilFruits();
        // then
        assertEquals(3, actual.size());
    }

    private static DevilFruitRecord getRecord() {
        return DevilFruitRecord.builder()
                .id(UUID.randomUUID())
                .code(GOMU_GOMU_NO_MI.code())
                .name(GOMU_GOMU_NO_MI.name())
                .type(GOMU_GOMU_NO_MI.type().name())
                .ability(GOMU_GOMU_NO_MI.ability())
                .build();
    }

    private static void assertMapping(DevilFruitRecord expectedRecord, DevilFruit actual) {
        assertEquals(expectedRecord.code(), actual.code());
        assertEquals(expectedRecord.name(), actual.name());
        assertEquals(DevilFruitType.valueOf(expectedRecord.type()), actual.type());
        assertEquals(expectedRecord.ability(), actual.ability());
    }

}