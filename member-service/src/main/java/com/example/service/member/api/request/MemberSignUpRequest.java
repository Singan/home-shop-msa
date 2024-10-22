package com.example.service.member.api.request;

public record MemberSignUpRequest(
        AuthInfoRequestDto authInfo,
        MemberInfoRequest memberInfo
) {}
