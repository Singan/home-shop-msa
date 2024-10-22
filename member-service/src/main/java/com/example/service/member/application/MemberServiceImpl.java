package com.example.service.member.application;

import com.example.service.member.api.request.AuthInfoRequestDto;
import com.example.service.member.api.request.MemberInfoRequest;
import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.application.dto.MemberSignUpDto;
import com.example.service.member.application.interfaces.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    public void memberSignUp(MemberSignUpRequest memberSignUpRequest) {
        memberSignUpRequest.authInfo();
        memberSignUpRequest.memberInfo();

    }
}
