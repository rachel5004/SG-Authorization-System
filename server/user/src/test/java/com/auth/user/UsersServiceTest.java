package com.auth.user;

import com.auth.user.controller.dto.UserResponseDto;
import com.auth.user.model.Role;
import com.auth.user.model.Users;
import com.auth.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class UsersServiceTest extends UserApplicationTests{
    @Autowired
    private UserService userService;

    String email = "admin@gmail.com";
    String password = "1234";
    UUID uid = UUID.fromString("84538c5a-0520-412e-8ad2-f7971af0c001");

    @Test
    void signIn() {
        Users user = Users.builder()
                .name("test")
                .email(email)
                .password(password)
                .role(Role.ROLE_ADMIN)
                .build();
        userService.signUp(user);
    }

    @Test
    void getAccountInfo() {
        Users user = userService.getAccountInfo(uid);
        System.out.println(user.getEmail());
    }

    @Test
    void getAllAccountInfo() {
        List<UserResponseDto> userList = userService.getAllAccountInfo();
        for (UserResponseDto user:userList) {
            System.out.println(user.getId());
        }
    }
}
