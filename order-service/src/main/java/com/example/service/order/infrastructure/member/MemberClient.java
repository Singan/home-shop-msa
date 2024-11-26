package com.example.service.order.infrastructure.member;

import com.example.service.order.infrastructure.member.dto.MemberInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "member-service", url = "${domain.url}${service.url.member-service}")
public interface MemberClient {

    @GetMapping("/members")
    MemberInfoDto getMemberProfile(@RequestHeader("Authorization")String token);
}
