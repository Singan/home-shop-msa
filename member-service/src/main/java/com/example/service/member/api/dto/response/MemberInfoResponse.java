package com.example.service.member.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record MemberInfoResponse(
        @NotNull
        String name,
        @NotNull
        String address,
        @NotNull
        String phone,
        @NotNull
        String email
) {
}
