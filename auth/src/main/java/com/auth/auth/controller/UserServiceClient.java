package com.auth.auth.controller;

import com.auth.auth.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AUTH-USER-SERVER")
public interface UserServiceClient {
    @PostMapping("/email")
    UserResponseDto getUserByEmail(@RequestParam String email);

    @GetMapping("/{id}")
    UserResponseDto getUserById(@PathVariable("id") String id);
}