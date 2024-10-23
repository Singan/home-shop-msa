package com.example.service.member.api.controller;

import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;
import com.example.service.member.application.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void signupMember(@RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberService.memberSignUp(memberSignUpRequest);
    }

    @PostMapping("/email/send")
    public void sendEmail(@RequestBody SendEmailRequest sendEmailRequest){
        memberService.sendEmail(sendEmailRequest);
    }
    @PostMapping("/email/confirm")
    public boolean confirmEmail(@RequestBody ConfirmEmailRequest confirmEmailRequest){
        return memberService.confirmEmail(confirmEmailRequest);
    }
}
