package com.auth.user.exception;


public class InternalServerException extends BaseException {

    public InternalServerException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}