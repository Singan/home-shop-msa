package com.example.service.member;

import com.example.service.member.application.dto.request.MemberSignUpDto;
import com.example.service.member.application.dto.response.MemberInfoDto;
import com.example.service.member.application.dto.response.MemberTokensDto;
import com.example.service.member.domain.Member;


public class MemberFactory {

    public static MemberSignUpDto createMemberSignDto(
            String loginId, String encodedPassword, String name, String phone
            , String address, String email) {
        return MemberSignUpDto.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .name(name)
                .phone(phone)
                .address(address)
                .email(email)
                .build();
    }

    // 여러 곳에서 사용할 수 있도록 객체로 받지 않도록 설계함.
    public static Member createDomain(String loginId, String password
            , String name, String phone, String address, String email) {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .address(address)
                .email(email)
                .name(name)
                .phone(phone)
                .build();
    }
    // 여러 곳에서 사용할 수 있도록 객체로 받지 않도록 설계함.

    public static MemberTokensDto createMemberTokensDto(String accessToken, String refreshToken) {
        return new MemberTokensDto(accessToken, refreshToken);
    }
    // 필드를 받는 것으로 통일할지 고민했으나. 도메인을 받는 것이 더 정확하다 생각했다.
    public static MemberInfoDto createMemberInfoDto(Member member) {
        return new MemberInfoDto(
                member.getName(),
                member.getAddress(),
                member.getPhone(),
                member.getEmail());
    }
}
