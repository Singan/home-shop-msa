package com.example.service.member.application.dto.response;

import com.example.service.member.api.dto.response.MemberLoginResponse;

public record MemberTokensDto (String accessToken , String refreshToken){
    public MemberLoginResponse getMemberLoginResponse(){
        return new MemberLoginResponse(accessToken , refreshToken);
    }
}
