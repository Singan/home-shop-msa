package com.example.service.order.infrastructure.repository;

import com.example.service.order.infrastructure.member.MemberClient;
import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberClient memberClient;

    @Async
    public CompletableFuture<MemberInfoDto> getMemberProfile() {
        return CompletableFuture.completedFuture(memberClient.getMemberProfile());
    }

}
