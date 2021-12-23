package com.auth.auth;

import com.auth.auth.dto.AuthenticationRequestDto;
import com.auth.auth.dto.TokenDto;
import com.auth.auth.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationTest extends AuthApplicationTests {
    @Autowired
    private AuthenticationService authService;

    String email = "email@gmail.com";
    String password = "password";

    @Test
    void authTest() {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(email,password);

        TokenDto tokenDto = authService.loginUsers(dto);
        System.out.println(tokenDto);
    }
}
