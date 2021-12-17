package com.auth.auth;

import com.auth.auth.dto.AuthenticationRequestDto;
import com.auth.auth.dto.TokenDto;
import com.auth.auth.model.Users;
import com.auth.auth.model.Role;
import com.auth.auth.repository.UsersRepository;
import com.auth.auth.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationTest extends AuthApplicationTests {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UsersRepository usersRepository;

    String email = "email@gmail.com";
    String password = "password";
    @Test
    void testUser() {
        Users user = Users.builder()
                .name("test")
                .email(email)
                .password(password)
                .role(Role.ROLE_ADMIN)
                .build();
        usersRepository.save(user);

        Users me = usersRepository.findByEmail(email).orElse(null);
        System.out.println(me.getRole());
    }

    @Test
    void authTest() {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(email,password);

        TokenDto tokenDto = authService.loginUsers(dto);
        System.out.println(tokenDto);
    }
}
