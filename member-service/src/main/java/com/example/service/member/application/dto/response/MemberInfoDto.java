package com.example.service.member.application.dto.response;

import com.example.service.member.api.dto.response.MemberInfoResponse;
import com.example.service.member.api.dto.response.MemberLoginResponse;
import jakarta.validation.constraints.NotNull;

public record MemberInfoDto(
        @NotNull
        String name,
        @NotNull
        String address,
        @NotNull
        String phone,
        @NotNull
        String email
) {
        public MemberInfoResponse getMemberInfoResponse(){
                return new MemberInfoResponse(name , address,phone,email);
        }
}
