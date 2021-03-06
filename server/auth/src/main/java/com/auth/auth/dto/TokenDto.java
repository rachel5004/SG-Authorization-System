package com.auth.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String name;
    private String email;
    private String roles;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}