package com.example.service.member.application.usecase;

import com.example.service.member.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMailConfirmUseCase {
    private final EmailRepository emailRepository;


    public boolean confirmEmailCode(String email , String code){
        return code.equals(emailRepository.findEmailCode(email));
    }
}
