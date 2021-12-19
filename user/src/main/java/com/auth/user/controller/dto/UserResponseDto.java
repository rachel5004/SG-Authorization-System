package com.auth.user.controller.dto;

import com.auth.user.model.Users;
import com.auth.user.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    @ApiModelProperty(example = "e18e3780-eac3-4122-b138-882900e0e167")
    private UUID id;
    @ApiModelProperty(example = "gildong")
    private String name;
    @ApiModelProperty(example = "gildong@gmail.com")
    private String email;
    @ApiModelProperty(example = "ROLE_USER")
    private Role role;
    @ApiModelProperty(example = "Register Success")
    private String message;

    public UserResponseDto(Users user) {
        BeanUtils.copyProperties(user, this);
    }
    public UserResponseDto(String message) {
        this.message = message;
    }
}