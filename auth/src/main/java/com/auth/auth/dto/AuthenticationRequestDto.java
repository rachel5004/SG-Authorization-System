package com.auth.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthenticationRequestDto {
    @NotBlank(message="EMAIL_IS_MANDATORY")
    @Email(message = "NOT_VALID_EMAIL")
    private String email;
    @NotBlank(message="PASSWORD_IS_MANDATORY")
    private String password;

    public AuthenticationRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
