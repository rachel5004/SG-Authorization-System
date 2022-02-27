package com.auth.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReissueDto {
    private String accessToken;
    private String refreshToken;
}
