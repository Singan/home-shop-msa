package com.example.service.member.api.dto.request;

public record MemberSignUpRequest(
        AuthInfoRequestDto authInfo,
        MemberInfoRequest memberInfo
) {

}
