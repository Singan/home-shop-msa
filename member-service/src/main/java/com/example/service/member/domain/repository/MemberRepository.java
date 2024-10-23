package com.example.service.member.domain.repository;

import com.example.service.member.domain.Member;
import com.example.service.member.infrastructure.entity.MemberEntity;

public interface MemberRepository {

    void memberSignUp(Member member);
}
