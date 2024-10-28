package com.example.service.member.application.usecase;

import com.example.service.member.application.MemberServiceFactory;
import com.example.service.member.application.dto.request.MemberSignUpDto;
import com.example.service.member.domain.Member;
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
                Member.builder().
                        loginId(memberSignUpDto.loginId()).
                        password(passwordEncoder.encode(memberSignUpDto.password())).
                        name(memberSignUpDto.name()).
                        phone(memberSignUpDto.phone()).
                        address(memberSignUpDto.address()).
                        email(memberSignUpDto.email()).
                        build()
        );
    }
}
