package com.example.service.member.api.request;

import com.example.service.member.application.dto.MemberSignUpDto;

public record MemberSignUpRequest(
        AuthInfoRequestDto authInfo,
        MemberInfoRequest memberInfo
) {

}
