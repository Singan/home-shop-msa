package com.example.service.member.domain.repository;

import com.example.service.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    void memberSignUp(Member member);

    Optional<Member> findMember(String loginId);
    Optional<Member> findMember(Long id);
}
