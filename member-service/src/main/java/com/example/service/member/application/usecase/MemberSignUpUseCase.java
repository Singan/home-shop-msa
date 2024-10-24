package com.example.service.member.application.usecase;

import com.example.service.member.MemberFactory;
import com.example.service.member.application.dto.request.MemberSignUpDto;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpUseCase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void memberSignUp(MemberSignUpDto memberSignUpDto) {
        memberRepository.memberSignUp(
                MemberFactory
                        .createDomain(
                                memberSignUpDto.loginId(),
                                passwordEncoder.encode(memberSignUpDto.password()),
                                memberSignUpDto.name(),
                                memberSignUpDto.phone(),
                                memberSignUpDto.address(),
                                memberSignUpDto.email()
                        )
        );
    }
}
