package com.example.service.member.infrastructure.repository;

import com.example.service.member.domain.Member;
import com.example.service.member.domain.repository.MemberRepository;
import com.example.service.member.infrastructure.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;
    @Override
    public void memberSignUp(Member member) {
        if (existMemberLoginId(member.getLoginId())) {
            throw new IllegalArgumentException("존재하는 유저입니다.");
        }

        jpaRepository.save(MemberEntity.fromDomain(member));
    }

    private boolean existMemberLoginId(String loginId) {
        return jpaRepository.existsByLoginId(loginId);
    }
}
