package com.example.service.member.api.controller;

import com.example.service.member.api.request.AuthenticationEmailKeyRequest;
import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.application.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public void signupMember(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberService.memberSignUp(memberSignUpRequest);
    }

    @PostMapping
    public void sendEmailNo(@RequestBody AuthenticationEmailKeyRequest inRedisKey){

    }
}
