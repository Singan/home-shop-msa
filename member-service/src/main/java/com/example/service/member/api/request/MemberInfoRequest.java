package com.example.service.member.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MemberInfoRequest(
        @NotNull(message = "이름은 필수입니다.")
        String name,

        @NotNull(message = "주소는 필수입니다.")
        String address,

        @NotNull(message = "전화번호는 필수입니다.")
        @NotEmpty(message = "전화번호는 비어있을 수 없습니다.")
        @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다.")
        String phone,


        @NotNull(message = "이메일은 필수입니다.")
        @NotEmpty(message = "이메일은 비어있을 수 없습니다.")
        @Email(message = "유효한 이메일 주소를 입력해 주세요.")
        String email
) {
}