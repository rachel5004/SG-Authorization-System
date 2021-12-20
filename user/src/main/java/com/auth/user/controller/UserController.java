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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import io.swagger.annotations.*;

@Api(tags={"User"})
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

    @ApiOperation(value = "User List", notes = "전체 회원 조회")
    @ApiResponse(code = 200, message = "전체 회원 리스트")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> listUser() {
        List<UserResponseDto> usersList = userService.getAllAccountInfo();
        return ResponseEntity.ok(usersList);
    }

    @ApiOperation(value = "User info", notes = "특정 회원 조회")
    @ApiImplicitParam(name = "id", value = "회원 ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공", response = UserResponseDto.class),
            @ApiResponse(code = 204, message = "존재하지 않는 회원"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> searchUser(@PathVariable final UUID id) {
        Users user = userService.getAccountInfo(id);
        if(user!=null) { return ResponseEntity.ok(new UserResponseDto(user)); }
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Sign In", notes = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이미 존재하는 회원", response = UserResponseDto.class),
            @ApiResponse(code = 201, message = "회원 가입 성공"),
    })
    @PostMapping
    public ResponseEntity<?> createUser(@Validated @RequestBody final SignupDto signupDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("404", "Validation failure", errors));
        }
        final Users user = userService.getAccountByEmail(signupDto.toEntity().getEmail());
        if(user==null) {
            userService.signUp(signupDto.toEntity());
            return new ResponseEntity(new UserResponseDto("Register Success!"),HttpStatus.CREATED
            );
        }
        return ResponseEntity.ok(new UserResponseDto("Account already exist"));
    }

    @ApiOperation(value = "User Unregister", notes = "회원 탈퇴")
    @ApiImplicitParam(name = "id", value = "회원 ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "탈퇴 성공"),
            @ApiResponse(code = 204, message = "존재하지 않는 회원"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean status = userService.signOut(id);
        if (status==true) return new ResponseEntity( HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
