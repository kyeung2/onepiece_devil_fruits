package nimbus.onepiece.devilfruits.interfaces;

import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;


@Builder
public record DevilFruit(
        @NonNull String code,
        @NonNull String name,
        @NonNull DevilFruitType type,
        @NonNull String ability) {
}
