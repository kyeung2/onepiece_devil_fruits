package com.nimbus.onepiece.devilfruits.sdk.errors;

import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;

public final class DevilFruitsClientException extends DevilFruitsGenericException {

    public DevilFruitsClientException(ErrorDto error, Throwable cause) {
        super(error, cause);
    }

}
