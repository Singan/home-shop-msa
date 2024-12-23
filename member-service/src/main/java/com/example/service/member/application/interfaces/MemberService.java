package com.example.service.member.application.interfaces;

import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberLoginRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;
import com.example.service.member.api.dto.response.MemberInfoResponse;
import com.example.service.member.api.dto.response.MemberLoginResponse;

public interface MemberService {

    void memberSignUp(MemberSignUpRequest memberSignUpRequest);


    void sendEmail(SendEmailRequest sendEmailRequest);

    boolean confirmEmail(ConfirmEmailRequest confirmEmailRequest);

    MemberLoginResponse memberLogin(MemberLoginRequest memberLoginRequest);

    MemberInfoResponse memberInfo(Long id);

}
