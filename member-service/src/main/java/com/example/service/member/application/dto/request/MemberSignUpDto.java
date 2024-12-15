package com.example.service.member.application.dto.request;


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
