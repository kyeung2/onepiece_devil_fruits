package nimbus.onepiece.devilfruits.domain;

import lombok.Builder;
import lombok.NonNull;


@Builder
public record DevilFruit(
        @NonNull String code,
        @NonNull String name,
        @NonNull DevilFruitType type,
        @NonNull String ability) {
}
