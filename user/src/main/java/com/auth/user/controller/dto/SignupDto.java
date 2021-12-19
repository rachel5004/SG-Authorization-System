package com.auth.user.controller.dto;

import com.auth.user.model.Users;
import com.auth.user.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    @ApiModelProperty(example = "gildong")
    @NotBlank(message="NAME_IS_MANDATORY")
    private String name;

    @ApiModelProperty(example = "$2a$10$dmeEm0rmcrflqpxe3HFVyetNVNvLS...")
    @NotBlank(message="PASSWORD_IS_MANDATORY")
    private String password;

    @ApiModelProperty(example = "gildong@gmail.com")
    @NotBlank(message="EMAIL_IS_MANDATORY")
    @Email(message = "NOT_VALID_EMAIL")
    private String email;

    public Users toEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.ROLE_USER)
                .build();
    }
    public Users toAdminEntity(){
        return Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.ROLE_ADMIN)
                .build();
    }
}
