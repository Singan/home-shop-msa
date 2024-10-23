package com.example.service.member.domain.repository;

public interface EmailRepository {
    String findEmailCode(String email);

    void saveEmailCode(String email, String code);
}
