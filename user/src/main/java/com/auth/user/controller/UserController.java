package com.auth.user.controller;

import com.auth.user.common.ErrorResponse;
import com.auth.user.controller.dto.SignupDto;
import com.auth.user.controller.dto.UserResponseDto;
import com.auth.user.model.Users;
import com.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMypageInfo() {
        Users user = userService.getMyInfo();
        if(user==null) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
        return ResponseEntity.ok(new UserResponseDto(user));
    }
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> listUser() {
        List<UserResponseDto> usersList = userService.getAllAccountInfo();
        return ResponseEntity.ok(usersList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> searchUser(@PathVariable final UUID id) {
        Users user = userService.getAccountInfo(id);
        if(user!=null) { return ResponseEntity.ok(new UserResponseDto(user)); }
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<?> createUSer(@Validated @RequestBody final SignupDto signupDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            // 200 response with 404 status code
            //return ResponseEntity.ok(new ErrorResponse("404", "Validation failure", errors));
            // 404 request
            return ResponseEntity.badRequest().body(new ErrorResponse("404", "Validation failure", errors));
        }
        final Users user = userService.getAccountByEmail(signupDto.toEntity().getEmail());
        if(user==null) {
            return new ResponseEntity(
                    new UserResponseDto(userService.signUp(signupDto.toEntity())),HttpStatus.CREATED
            );
        }
        return ResponseEntity.ok(new UserResponseDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean status = userService.signOut(id);
        if (status==true) return new ResponseEntity( HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
