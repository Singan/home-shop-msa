package com.example.service.member.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    @NotNull
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @Builder

    public Member(Long id, String loginId, String password, String name, String address, String phone, String email) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
