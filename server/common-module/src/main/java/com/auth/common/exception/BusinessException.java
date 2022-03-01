package com.auth.common.exception;

import com.auth.common.domain.ResponseType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ResponseType responseType;

    public BusinessException(ResponseType responseType) {
        this.responseType = responseType;
    }

}
