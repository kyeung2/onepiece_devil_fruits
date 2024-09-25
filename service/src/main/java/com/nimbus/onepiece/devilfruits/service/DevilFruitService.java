package com.nimbus.onepiece.devilfruits.service;

import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.domain.DevilFruitType;
import com.nimbus.onepiece.devilfruits.persistence.DevilFruitRepository;
import com.nimbus.onepiece.devilfruits.persistence.records.DevilFruitRecord;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DevilFruitService {

    private final DevilFruitRepository repository;

    public Optional<DevilFruit> getDevilFruit(@NonNull String code) {
        return repository.findByCode(code)
                .map(mapToDomain());
    }

    public Collection<DevilFruit> getAllDevilFruits() {
        return repository.findAll().stream()
                .map(mapToDomain())
                .toList();
    }

    private static Function<DevilFruitRecord, DevilFruit> mapToDomain() {
        return r -> DevilFruit.builder()
                .code(r.code())
                .name(r.name())
                .type(DevilFruitType.valueOf(r.type()))
                .ability(r.ability())
                .build();
    }
}
