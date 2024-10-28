package com.example.service.member.application;

import com.example.service.member.api.MemberApiFactory;
import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberLoginRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;
import com.example.service.member.api.dto.response.MemberInfoResponse;
import com.example.service.member.api.dto.response.MemberLoginResponse;
import com.example.service.member.application.interfaces.MemberService;
import com.example.service.member.application.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// request DTO 를 받아
// response DTO 를 넘겨줘야한다.
public class MemberServiceImpl implements MemberService {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberMailSendUseCase memberMailSendUseCase;
    private final MemberMailConfirmUseCase mailConfirmUseCase;
    private final MemberLoginUseCase memberLoginUseCase;
    private final MemberMyPageUseCase memberMyPageUseCase;

    public void memberSignUp(MemberSignUpRequest memberSignUpRequest) {

        memberSignUpUseCase.memberSignUp(
                MemberServiceFactory.createMemberSignUpDto(memberSignUpRequest)
        );

    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {
        memberMailSendUseCase.sendEmail(sendEmailRequest.email());
    }

    @Override
    public boolean confirmEmail(ConfirmEmailRequest confirmEmailRequest) {
        return mailConfirmUseCase.confirmEmailCode(
                confirmEmailRequest.email(),
                confirmEmailRequest.code()
        );
    }

    @Override
    public MemberLoginResponse memberLogin(MemberLoginRequest memberLoginRequest) {
        return MemberApiFactory.createMemberLoginResponse(memberLoginUseCase.memberLogin(
                memberLoginRequest.id(),
                memberLoginRequest.password()
        ));

    }

    @Override
    public MemberInfoResponse memberInfo(Long id) {
        return MemberApiFactory.createMemberInfoResponse(memberMyPageUseCase.findMemberMyPage(id));
    }
}
