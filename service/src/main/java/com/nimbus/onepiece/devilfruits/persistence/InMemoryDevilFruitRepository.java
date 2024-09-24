package com.nimbus.onepiece.devilfruits.persistence;

import lombok.NonNull;
import com.nimbus.onepiece.devilfruits.config.DevilFruitsData;
import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDevilFruitRepository {

    private final List<DevilFruit> devilFruits;

    public InMemoryDevilFruitRepository(DevilFruitsData properties) {

        devilFruits = properties.getDevilFruits().stream()
                .map(prop -> DevilFruit.builder()
                        .code(prop.getCode())
                        .name(prop.getName())
                        .type(prop.getType())
                        .ability(prop.getAbility())
                        .build())
                .toList();
    }

    public Optional<DevilFruit> find(@NonNull String code) {
        return devilFruits.stream()
                .filter(df -> df.code().equals(code))
                .findFirst();
    }

    public Collection<DevilFruit> findAll() {
        return devilFruits;
    }
}
