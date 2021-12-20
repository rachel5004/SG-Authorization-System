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
                .name(users.getName())
                .email(users.getEmail())
                .roles(users.getRole())
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

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
        } catch (SignatureException e) {
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    public Claims getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        return claims;
    }
}
