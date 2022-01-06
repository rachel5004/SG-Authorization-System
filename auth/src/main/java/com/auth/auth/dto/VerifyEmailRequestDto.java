package com.auth.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class VerifyEmailRequestDto {
    @Email(message = "NOT_VALID_EMAIL")
    private String email;
    @NotBlank(message="CODE_IS_MANDATORY")
    private String code;
}
