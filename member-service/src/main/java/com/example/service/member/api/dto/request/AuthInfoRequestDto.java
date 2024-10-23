package com.example.service.member.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthInfoRequestDto(
        @NotNull(message = "ID는 필수입니다.")
        @NotEmpty(message = "ID는 비어있을 수 없습니다.")
        String id,
        @NotNull(message = "비밀번호는 필수입니다.")
        @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
        String password
) {
}
