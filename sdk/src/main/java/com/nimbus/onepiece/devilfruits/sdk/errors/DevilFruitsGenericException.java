package com.nimbus.onepiece.devilfruits.sdk.errors;

import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;
import lombok.Getter;

@Getter
public sealed class DevilFruitsGenericException extends RuntimeException
        permits DevilFruitsClientException, DevilFruitsServerException {

    private final ErrorDto error;

    public DevilFruitsGenericException(ErrorDto error, Throwable cause) {
        super(cause);
        this.error = error;
    }

}
