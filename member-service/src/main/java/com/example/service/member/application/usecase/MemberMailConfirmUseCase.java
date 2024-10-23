package com.example.service.member.application.usecase;

import com.example.service.member.domain.repository.EmailRepository;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMailConfirmUseCase {
    private final EmailRepository emailRepository;


    public boolean confirmEmailCode(String email , String code){
        return emailRepository.findEmailCode(email).equals(code);
    }
}
