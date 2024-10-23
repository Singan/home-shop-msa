package com.example.service.member.application;

import com.example.service.member.MemberFactory;
import com.example.service.member.api.request.AuthInfoRequestDto;
import com.example.service.member.api.request.MemberInfoRequest;
import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.application.dto.MemberSignUpDto;
import com.example.service.member.application.interfaces.MemberService;
import com.example.service.member.application.usecase.MemberAuthenticationEmailUseCase;
import com.example.service.member.application.usecase.MemberSignUpUseCase;
import com.example.service.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberSignUpUseCase memberSignUpUseCase;
    private final MemberAuthenticationEmailUseCase memberAuthenticationEmailUseCase;

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
    public void authenticationEmail(String key) {

    }
}
