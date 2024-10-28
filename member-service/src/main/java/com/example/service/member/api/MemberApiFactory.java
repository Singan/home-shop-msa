package com.example.service.member.api;

import com.example.service.member.api.dto.response.MemberInfoResponse;
import com.example.service.member.api.dto.response.MemberLoginResponse;
import com.example.service.member.application.dto.response.MemberInfoDto;
import com.example.service.member.application.dto.response.MemberTokensDto;

public class MemberApiFactory {

    public static MemberLoginResponse createMemberLoginResponse(MemberTokensDto memberTokensDto){
        return new MemberLoginResponse(memberTokensDto.accessToken() , memberTokensDto.refreshToken());
    }

    public static MemberInfoResponse createMemberInfoResponse(MemberInfoDto memberInfoDto){

            return new MemberInfoResponse(memberInfoDto.name() , memberInfoDto.address(),memberInfoDto.phone(),memberInfoDto.email());

    }
}
