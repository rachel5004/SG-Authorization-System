package com.auth.auth.service;

import com.auth.auth.config.RedisUtil;
import com.auth.auth.dto.VerifyEmailRequestDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class VerifyEmailService{
    private final RedisUtil redisUtil;
    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(String to,String sub, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(sub);
        message.setText(text);
        emailSender.send(message);
    }
    public String sendVerificationMail(String email) throws NotFoundException {
        if(email==null) throw new NotFoundException("멤버가 조회되지 않음");
        String verifyCode = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        // redis에 verify code 정보 저장

        System.out.println(email);
        redisUtil.setDataExpire(email,verifyCode, 60 * 3L);
        // 인증 링크 전송
        sendMail(email,"이메일 인증 코드입니다.",verifyCode);
        return verifyCode;
    }
    public void verifyCode(String email, String code) throws NotFoundException {
        String memberCode = redisUtil.getData(email);
        System.out.println(email);
        System.out.println(memberCode);
        if(memberCode==null||!code.equals(memberCode)) throw new NotFoundException("유효하지 않은 코드입니다.");
        redisUtil.deleteData(email);
    }
}