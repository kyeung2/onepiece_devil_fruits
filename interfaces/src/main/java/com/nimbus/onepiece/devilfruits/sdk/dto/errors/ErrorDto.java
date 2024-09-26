package com.nimbus.onepiece.devilfruits.sdk.dto.errors;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record ErrorDto(
        @NonNull String code,
        @NonNull String message) {
}
