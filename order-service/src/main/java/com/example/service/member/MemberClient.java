package com.example.service.member;

import com.example.service.member.dto.MemberInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "member-service", url = "${domain.url}${service.url.member-service}")
public interface MemberClient {

    @GetMapping("/members")
    MemberInfoDto getMemberProfile();
}
