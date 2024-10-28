package com.example.service.member.application;

import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.application.dto.request.MemberSignUpDto;
import com.example.service.member.application.dto.response.MemberInfoDto;
import com.example.service.member.application.dto.response.MemberTokensDto;
import com.example.service.member.domain.Member;

public class MemberServiceFactory {

    public static MemberSignUpDto createMemberSignUpDto(MemberSignUpRequest memberSignUpRequest) {

        return MemberSignUpDto.builder()
                .loginId(memberSignUpRequest.authInfo().id())
                .password(memberSignUpRequest.authInfo().password())
                .name(memberSignUpRequest.memberInfo().name())
                .phone(memberSignUpRequest.memberInfo().phone())
                .email(memberSignUpRequest.memberInfo().email())
                .address(memberSignUpRequest.memberInfo().address())
                .build();
    }



    public static MemberTokensDto createMemberTokensDto(String accessToken, String refreshToken) {
        return new MemberTokensDto(accessToken, refreshToken);
    }

    public static MemberInfoDto createMemberInfoDto(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member object cannot be null");
        }
        return new MemberInfoDto(
                member.getName(),
                member.getAddress(),
                member.getPhone(),
                member.getEmail());
    }
}
