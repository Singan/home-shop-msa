package com.example.service.order.infrastructure.repository;

import com.example.service.order.infrastructure.member.MemberClient;
import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberClient memberClient;

    public MemberInfoDto getMemberProfile(){
        return memberClient.getMemberProfile();
    };

}
