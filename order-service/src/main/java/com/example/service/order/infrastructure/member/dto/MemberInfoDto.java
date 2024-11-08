package com.example.service.order.infrastructure.member.dto;

public record MemberInfoDto(
        String name,
        String address,
        String phone,
        String email
) {
}
