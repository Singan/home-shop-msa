package com.example.service.member.infrastructure.repository;

import com.example.service.member.infrastructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity , Long> {

    boolean existsByLoginId(String loginId);
}
