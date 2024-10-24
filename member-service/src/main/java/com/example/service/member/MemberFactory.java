package com.example.service.member;

import com.example.service.member.application.dto.request.MemberSignUpDto;
import com.example.service.member.application.dto.response.MemberTokensDto;
import com.example.service.member.domain.Member;


public class MemberFactory {

    public static MemberSignUpDto createMemberSignDto(
            String loginId, String encodedPassword , String name , String phone
            , String address , String email){
        return MemberSignUpDto.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .name(name)
                .phone(phone)
                .address(address)
                .email(email)
                .build();
    }

    public static Member createDomain(String loginId , String password
            , String name , String phone,String address , String email){
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .address(address)
                .email(email)
                .name(name)
                .phone(phone)
                .build();
    }

    public static MemberTokensDto createMemberTokensDto (String accessToken , String refreshToken){
        return new MemberTokensDto(accessToken , refreshToken);
    }
}
