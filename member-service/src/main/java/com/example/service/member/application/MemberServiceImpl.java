package com.example.service.member.application;

import com.example.service.member.MemberFactory;
import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.api.request.SendEmailAuthentication;
import com.example.service.member.application.interfaces.MemberService;
import com.example.service.member.application.usecase.MemberMailSendUseCase;
import com.example.service.member.application.usecase.MemberSignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberMailSendUseCase memberMailSendUseCase;

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
    public void sendEmail(SendEmailAuthentication sendEmailAuthentication) {
        memberMailSendUseCase.sendEmail(sendEmailAuthentication.email());
    }


}
