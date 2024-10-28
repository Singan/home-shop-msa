package com.example.service.member.application.usecase;

import com.example.service.member.application.MemberServiceFactory;
import com.example.service.member.application.dto.response.MemberInfoDto;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMyPageUseCase {
    private final MemberRepository memberRepository;

    public MemberInfoDto findMemberMyPage(Long id){
        return MemberServiceFactory.createMemberInfoDto(memberRepository.findMember(id).orElseThrow());
    }
}
