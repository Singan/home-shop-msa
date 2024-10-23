package com.example.service.member.infrastructure.repository;

import com.example.service.member.domain.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailRepository {
    private final MemberRedisRepository redisRepository;


    @Override
    public String findEmailCode(String email){
        String rCode = redisRepository.findCode(email);
        return rCode;
    }

    @Override
    public void saveEmailCode(String email, String code){
        redisRepository.saveCode(email,code);
    }
}
