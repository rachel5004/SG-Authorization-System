package com.auth.auth.service;

import com.auth.auth.config.RedisUtil;
import com.auth.auth.config.SaltUtil;
import com.auth.auth.controller.UserServiceClient;
import com.auth.auth.dto.AuthenticationRequestDto;
import com.auth.auth.dto.TokenDto;
import com.auth.auth.dto.TokenReissueDto;
import com.auth.auth.dto.UserResponseDto;
import com.auth.auth.jwt.JwtTokenProvider;
import com.auth.auth.model.Users;
import com.auth.auth.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;
    private final SaltUtil saltUtil;

//    private final RestTemplate restTemplate;
    private final UserServiceClient userServiceClient;

    @Transactional
    public TokenDto loginUsers(AuthenticationRequestDto dto) {

        UserResponseDto user = userServiceClient.getUserByEmail(dto.getEmail());
        System.out.println(user);
        // id/pwd로 유저 객체 불러오기
//        Users users = usersRepository.findByEmail(dto.getEmail()).orElse(null);
        // 없는 유저/비번틀림 의 경우 익셉션 -> 통합 or 커스텀으로 분리?
        String salt = user.getSalt();
        String password = saltUtil.encodePassword(salt,dto.getPassword());
        if(user ==null||!user.getPassword().equals(password)){
            throw new IllegalArgumentException();
        }
        // DB에 있으면 불러온 user로 JWT 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.generateToken(user);
        redisUtil.setDataExpire(user.getName(),tokenDto.getRefreshToken(), 60 * 30L);
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenReissueDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
        // get user ID from Access Token
        Claims claims = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // get Refresh Token
        String refreshToken = redisUtil.getData((String) claims.get("name"));
        if (refreshToken==null) throw new RuntimeException("로그아웃 된 사용자입니다.");
        if (!refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }
        UserResponseDto users = userServiceClient.getUserById((String) claims.get("id"));
        TokenDto tokenDto = jwtTokenProvider.generateToken(users);
        redisUtil.setDataExpire(users.getName(),tokenDto.getRefreshToken(), 60 * 30L);
        return tokenDto;
    }

}
