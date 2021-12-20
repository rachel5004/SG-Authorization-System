package com.auth.user.service;

import com.auth.user.config.SaltUtil;
import com.auth.user.common.SecurityUtil;
import com.auth.user.controller.dto.UserResponseDto;
import com.auth.user.model.Salt;
import com.auth.user.model.Users;
import com.auth.user.repository.SaltRepository;
import com.auth.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final SaltRepository saltRepository;
    private final SaltUtil saltUtil;

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllAccountInfo(){
        return usersRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Users getAccountInfo(UUID id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Users getAccountByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public Users signUp(Users user) {
        String salt = saltUtil.genSalt();
        System.out.println(salt);
        user.setSalt(new Salt(salt));
        user.setPassword(saltUtil.encodePassword(salt, user.getPassword()));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Transactional
    public boolean signOut(UUID id) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            usersRepository.delete(user);
            return true;
        }
        return false;
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public Users getMyInfo() {
        return usersRepository.findById(SecurityUtil.getCurrentMemberId()).orElse(null);
    }
}

