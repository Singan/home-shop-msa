package com.example.service.member.application;

import com.example.service.member.MemberFactory;
import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberLoginRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;
import com.example.service.member.api.dto.response.MemberInfoResponse;
import com.example.service.member.api.dto.response.MemberLoginResponse;
import com.example.service.member.application.dto.response.MemberInfoDto;
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
                MemberFactory.createMemberSignDto(
                        memberSignUpRequest.authInfo().id(),
                        memberSignUpRequest.authInfo().password(),
                        memberSignUpRequest.memberInfo().name(),
                        memberSignUpRequest.memberInfo().phone(),
                        memberSignUpRequest.memberInfo().address(),
                        memberSignUpRequest.memberInfo().email()
                )
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
        return memberLoginUseCase.memberLogin(
                        memberLoginRequest.id(),
                        memberLoginRequest.password()
                )
                .getMemberLoginResponse();
    }

    @Override
    public MemberInfoResponse memberInfo(Long id) {
        return memberMyPageUseCase.findMemberMyPage(id).getMemberInfoResponse();
    }
}
