package com.auth.common.exception;

import com.auth.common.domain.ResponseType;

public class NotFoundException extends BusinessException {

    public NotFoundException(ResponseType type) {
        super(type);
    }

}
