package com.example.service.member.application.usecase;

import com.example.service.member.MemberFactory;
import com.example.service.member.application.dto.response.MemberInfoDto;
import com.example.service.member.domain.Member;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMyPageUseCase {
    private final MemberRepository memberRepository;
    private final static int dsfsdf=2;

    public MemberInfoDto findMemberMyPage(Long id){
        return MemberFactory.createMemberInfoDto(memberRepository.findMember(id).orElseThrow());
    }
}
