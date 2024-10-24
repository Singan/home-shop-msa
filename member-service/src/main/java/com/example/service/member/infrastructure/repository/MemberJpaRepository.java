package com.example.service.member.infrastructure.repository;

import com.example.service.member.infrastructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity , Long> {

    boolean existsByLoginId(String loginId);

    Optional<MemberEntity> findByLoginId(String loginId);
}
