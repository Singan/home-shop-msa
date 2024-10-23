package com.example.service.member.application.usecase;

import com.example.service.member.MemberFactory;
import com.example.service.member.application.dto.MemberSignUpDto;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpUseCase {

    private final MemberRepository memberRepository;

    public void memberSignUp(MemberSignUpDto memberSignUpDto) {
        memberRepository.memberSignUp(
                MemberFactory
                        .createDomain(
                                memberSignUpDto.loginId(),
                                memberSignUpDto.password(),
                                memberSignUpDto.name(),
                                memberSignUpDto.phone(),
                                memberSignUpDto.address(),
                                memberSignUpDto.email()
                        )
        );
    }
}
