package com.example.service.member.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Member {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String email;

}
