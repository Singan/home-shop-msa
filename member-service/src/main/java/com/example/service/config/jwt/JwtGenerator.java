package com.example.service.config.jwt;

import com.example.service.member.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    @Value("${jwt.access.secret}")
    private String accessKey;
    @Value("${jwt.refresh.secret}")
    private String refreshKey;
    private final long accessExpiration = 86400000;

    private final long refreshExpiration = 86400000 * 3;
    public String generateAccessToken(Member member) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setSubject(String.valueOf(member.getId()))
                .setExpiration(new Date(now + accessExpiration))  // 만료 시간 추가
                .signWith(SignatureAlgorithm.HS256, accessKey)
                .compact();
    }

    public String generateRefreshToken(Member member) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setHeader(createHeader())
                .setSubject(member.getId().toString())
                .setExpiration(new Date(now + refreshExpiration))  // 만료 시간 추가
                .signWith( SignatureAlgorithm.HS256,refreshKey)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", member.getName());
        return claims;
    }
}