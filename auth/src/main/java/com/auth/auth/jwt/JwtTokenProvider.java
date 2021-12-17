package com.auth.auth.jwt;

import com.auth.auth.dto.TokenDto;
import com.auth.auth.model.Users;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.*;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = Duration.ofHours(9).toMillis(); // 9시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
    private static final String AUTHORITIES_KEY = "auth";


    public TokenDto generateToken(Users users) {
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken =  Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(users))
                .setIssuedAt(now)  // 발급 시간
                .setExpiration(new Date(now.getTime()+ ACCESS_TOKEN_EXPIRE_TIME)) // 유효기간 3시간
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret())
                .compact();

        return TokenDto.builder()
                .grantType(jwtProperties.getTokenPrefix())
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        return header;
    }

    private Claims createClaims(Users users) { // payload
        Claims claims = Jwts.claims().setSubject(users.getName());
        claims.put("id", users.getId());
        claims.put("name", users.getName());
        claims.put("email", users.getEmail());
        claims.put(AUTHORITIES_KEY, users.getRole());
        return claims;
    }
}
