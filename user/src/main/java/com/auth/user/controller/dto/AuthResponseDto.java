package com.auth.user.controller.dto;

import com.auth.user.model.Role;
import com.auth.user.model.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String salt;
    private String role;

    public AuthResponseDto(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.salt = user.getSalt().getSalt();
        this.role = String.valueOf(user.getRole());
    }
}
