package com.example.service.member.application.dto.response;

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
}
