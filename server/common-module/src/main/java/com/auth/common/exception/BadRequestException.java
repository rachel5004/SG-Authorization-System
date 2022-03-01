package com.auth.common.exception;

import com.auth.common.domain.ResponseType;

public class BadRequestException extends BusinessException {

    public BadRequestException(ResponseType type) {
        super(type);
    }

}
