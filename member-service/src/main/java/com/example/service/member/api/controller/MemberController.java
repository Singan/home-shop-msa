package com.example.service.member.api.controller;

import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.api.request.SendEmailAuthentication;
import com.example.service.member.application.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void signupMember(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberService.memberSignUp(memberSignUpRequest);
    }

    @PostMapping("/send/mails")
    public void sendEmailNo(@RequestBody SendEmailAuthentication sendEmailAuthentication){
        memberService.sendEmail(sendEmailAuthentication);
    }
}
