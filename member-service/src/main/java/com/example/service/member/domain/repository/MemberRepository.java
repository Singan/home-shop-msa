package com.example.service.member.domain.repository;

import com.example.service.member.domain.Member;
import com.example.service.member.infrastructure.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

    void memberSignUp(Member member);

    Optional<Member> findMember(String id);
}
