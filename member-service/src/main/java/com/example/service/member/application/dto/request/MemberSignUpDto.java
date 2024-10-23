package com.example.service.member.application.dto.request;


import com.example.service.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberSignUpDto(

        String loginId,
        String password,
        String name,
        String address,
        String phone,
        String email
) {


}
