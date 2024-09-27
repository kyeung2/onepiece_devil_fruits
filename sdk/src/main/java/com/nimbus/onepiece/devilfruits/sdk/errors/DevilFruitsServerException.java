package com.nimbus.onepiece.devilfruits.sdk.errors;

import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;

public final class DevilFruitsServerException extends DevilFruitsGenericException {

    public DevilFruitsServerException(ErrorDto error, Throwable cause) {
        super(error, cause);
    }
}

