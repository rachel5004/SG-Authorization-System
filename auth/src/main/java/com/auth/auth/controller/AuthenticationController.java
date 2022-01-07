package com.auth.auth.controller;

import com.auth.auth.dto.*;
import com.auth.auth.service.AuthenticationService;
import com.auth.auth.service.VerifyEmailService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Auth"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authService;
    private final VerifyEmailService verifyEmailService;

    @ApiOperation(value = "Login", notes = "회원 로그인 및 토큰 발급.")
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

    @PostMapping("/verify/send")
    public ResponseEntity<String> sendVerifyEmail(@RequestBody SendEmailRequestDto dto) throws NotFoundException {
        System.out.println("request arrived");
        String verifyCode = verifyEmailService.sendVerificationMail(dto.getEmail());
        return ResponseEntity.ok(verifyCode);
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyEmailRequestDto dto) throws NotFoundException {
        verifyEmailService.verifyCode(dto.getEmail(),dto.getCode());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenReissueDto tokenReissueDto) {
        return ResponseEntity.ok(authService.reissue(tokenReissueDto));
    }
}
