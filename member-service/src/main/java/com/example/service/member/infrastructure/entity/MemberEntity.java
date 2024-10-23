package com.example.service.member.infrastructure.entity;

import com.example.service.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)

    private String loginId;
    private String password;

    private String name;
    private String email;
    private String phone;
    private String address;
    @Builder
    public MemberEntity(Long id, String loginId, String password, String name, String email, String phone, String address) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Member toDomain() {
        return Member
                .builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }

    public static MemberEntity fromDomain(Member member) {
        return MemberEntity
                .builder()
                .id(member.getId())
                .password(member.getPassword())
                .loginId(member.getLoginId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();

    }
}
