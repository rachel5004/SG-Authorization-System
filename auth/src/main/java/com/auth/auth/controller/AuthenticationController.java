package com.auth.auth.controller;

import com.auth.auth.dto.AuthenticationRequestDto;
import com.auth.auth.dto.TokenDto;
import com.auth.auth.service.AuthenticationService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"login"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authService;

    @ApiOperation(value = "Login", notes = "회원 번호를 기반으로 특정 회원의 정보를 검색합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "토큰 발급", response = TokenDto.class),
            @ApiResponse(code = 204, message = "이메일/비밀번호 오류"),
    })
    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody @ApiParam(value = "이메일,비밀번호", required = true)
                                                   AuthenticationRequestDto dto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlAllowOrigin("*");
        httpHeaders.setAccessControlAllowCredentials(true);
        TokenDto loginResponseDto = authService.loginUsers(dto);
        if (loginResponseDto!=null) return ResponseEntity.ok(loginResponseDto);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
