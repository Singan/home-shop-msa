package com.example.service.member.application.usecase;

import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpUseCase {

    private final MemberRepository memberRepository;

    public void memberSignUp(){

    }
}
