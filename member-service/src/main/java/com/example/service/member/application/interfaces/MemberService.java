package com.example.service.member.application.interfaces;

import com.example.service.member.api.request.AuthInfoRequestDto;
import com.example.service.member.api.request.MemberSignUpRequest;
import com.example.service.member.application.dto.MemberSignUpDto;
import org.springframework.stereotype.Service;

public interface MemberService {

    void memberSignUp(MemberSignUpRequest memberSignUpRequest);
}
