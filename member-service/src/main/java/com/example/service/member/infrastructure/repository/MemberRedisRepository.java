package com.example.service.member.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class MemberRedisRepository {
    private final StringRedisTemplate stringRedisTemplate;


    public String findCode(String email){
        String value = stringRedisTemplate.opsForValue().get(email);
        return value;
    }
    public void saveCode(String email , String code){
        stringRedisTemplate.opsForValue().set(email,code , Duration.ofMinutes(3));
    }

}
