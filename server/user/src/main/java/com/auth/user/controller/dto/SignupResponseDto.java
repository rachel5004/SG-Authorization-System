package com.auth.user.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class SignupResponseDto {
    @ApiModelProperty(example = "Register Success")
    private String message;
}
