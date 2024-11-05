package com.example.service.member.application.usecase;

import com.example.config.jwt.JwtGenerator;
import com.example.service.member.application.MemberServiceFactory;
import com.example.service.member.application.dto.response.MemberTokensDto;
import com.example.service.member.domain.Member;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginUseCase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    public MemberTokensDto memberLogin(String loginId, String password) {
        Member member = memberRepository.findMember(loginId).orElseThrow();

        if (!matches(member.getPassword(), password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }


        String accessToken = jwtGenerator.generateAccessToken(member);
        String refreshToken = jwtGenerator.generateRefreshToken(member);

        return MemberServiceFactory.createMemberTokensDto(accessToken, refreshToken);
    }


    private boolean matches(String encodedPassword, String password) {

        return passwordEncoder.matches( password , encodedPassword);
    }
}
