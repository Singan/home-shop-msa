package com.example.service.member.application.interfaces;

import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;

public interface MemberService {

    void memberSignUp(MemberSignUpRequest memberSignUpRequest);


    void sendEmail(SendEmailRequest sendEmailRequest);

    boolean confirmEmail(ConfirmEmailRequest confirmEmailRequest);
}
