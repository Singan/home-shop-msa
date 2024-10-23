package com.example.service.member;

import com.example.service.member.application.dto.MemberSignUpDto;
import com.example.service.member.domain.Member;
import org.springframework.data.domain.Page;


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
}
