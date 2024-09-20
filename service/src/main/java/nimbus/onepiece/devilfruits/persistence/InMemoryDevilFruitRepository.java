package nimbus.onepiece.devilfruits.persistence;

import lombok.NonNull;
import nimbus.onepiece.devilfruits.config.DevilFruitsData;
import nimbus.onepiece.devilfruits.domain.DevilFruit;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<DevilFruit> find(@NonNull String code) {
        return devilFruits.stream()
                .filter(df -> df.code().equals(code))
                .findFirst()
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    public Flux<DevilFruit> findAll() {
        return Flux.fromIterable(devilFruits);
    }
}
