package com.example.service.member.application;

import com.example.service.member.MemberFactory;
import com.example.service.member.api.dto.request.ConfirmEmailRequest;
import com.example.service.member.api.dto.request.MemberSignUpRequest;
import com.example.service.member.api.dto.request.SendEmailRequest;
import com.example.service.member.application.interfaces.MemberService;
import com.example.service.member.application.usecase.MemberMailConfirmUseCase;
import com.example.service.member.application.usecase.MemberMailSendUseCase;
import com.example.service.member.application.usecase.MemberSignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberMailSendUseCase memberMailSendUseCase;
    private final MemberMailConfirmUseCase mailConfirmUseCase;

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
}
