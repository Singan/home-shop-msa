package com.example.service.member.infrastructure.repository;

import com.example.service.member.domain.Member;
import com.example.service.member.domain.repository.MemberRepository;
import com.example.service.member.infrastructure.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;

    @Override
    public void memberSignUp(Member member) {
        if (existMemberLoginId(member.getLoginId())) {
            throw new IllegalArgumentException("존재하는 유저입니다.");
        }

        jpaRepository.save(MemberEntity.fromEntity(member));
    }

    @Override
    public Optional<Member> findMember(String id) {


        return jpaRepository.findByLoginId(id).map(MemberEntity::toDomain);
    }

    @Override
    public Optional<Member> findMember(Long id) {
        return jpaRepository.findById(id).map(MemberEntity::toDomain);
    }


    private boolean existMemberLoginId(String loginId) {
        return jpaRepository.existsByLoginId(loginId);
    }
}
