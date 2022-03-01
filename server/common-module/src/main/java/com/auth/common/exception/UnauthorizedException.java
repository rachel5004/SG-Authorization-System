package com.auth.common.exception;

import com.auth.common.domain.ResponseType;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ResponseType responseType) {
        super(responseType);
    }

}
