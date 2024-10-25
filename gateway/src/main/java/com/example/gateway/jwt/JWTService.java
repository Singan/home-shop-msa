package com.example.gateway.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JWTService {

    private final String signingKey;

    public JWTService(@Value("${access.key}") String signingKey) {
        this.signingKey = signingKey;
    }

    public String parseToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
            log.debug("JWT claims: {}", claims);
            log.debug("JWT subject: {}", claims.getSubject());

            return claims.getSubject();
        } catch (Exception e) {
            log.error("JWT 검증 실패: {}", e.getMessage());
            return null;
        }
    }
}
