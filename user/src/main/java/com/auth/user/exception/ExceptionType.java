package com.auth.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionType {

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일이 있습니다."),
    NOT_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "존재하지 않은 이메일입니다."),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 옳지 않습니다."),
    NOT_EXIST_USER_ID(HttpStatus.BAD_REQUEST, "존재하지 않은 사용자입니다."),

    NOT_AUTHORIZED(HttpStatus.BAD_REQUEST, "로그인되지 않은 사용자입니다.");

    private final HttpStatus httpStatus;

    private final String message;
}