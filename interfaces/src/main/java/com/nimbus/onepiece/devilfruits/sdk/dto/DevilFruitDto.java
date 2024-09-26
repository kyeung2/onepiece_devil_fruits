package com.nimbus.onepiece.devilfruits.sdk.dto;

import lombok.Builder;
import lombok.NonNull;


@Builder
public record DevilFruitDto(
        @NonNull String code,
        @NonNull String name,
        @NonNull DevilFruitTypeDto type,
        @NonNull String ability) {
}
