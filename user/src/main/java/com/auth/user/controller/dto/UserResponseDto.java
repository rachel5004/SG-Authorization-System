package com.auth.user.controller.dto;

import com.auth.user.model.Users;
import com.auth.user.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private Role role;

    public UserResponseDto(Users user) {
        BeanUtils.copyProperties(user, this);
    }
}