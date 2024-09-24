package com.nimbus.onepiece.devilfruits.persistence.records;

import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public record DevilFruitRecord(
       @NonNull UUID id,
       @NonNull String code,
       @NonNull String name,
       @NonNull String type,
       @NonNull String ability) {
}
