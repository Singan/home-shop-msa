package com.example.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    private final SecretKey signingKey;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        byte[] secretKeyBytes = Base64.getEncoder().encode(env.getProperty("access.key").getBytes());
        signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "토큰이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED);
            }

            String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            String jwt = token.replace("Bearer ", "");
            String userId = parseToken(jwt);

            if (userId == null) {
                return onError(exchange, "잘못된 토큰입니다.", HttpStatus.UNAUTHORIZED);
            }

            // 사용자 ID를 새로운 헤더에 추가
            ServerHttpRequest updatedRequest = request.mutate()
                    .header("X-User-Id", userId)
                    .build();


            exchange = exchange.mutate().request(updatedRequest).build();

            return chain.filter(exchange);
        };
    }

    private String parseToken(String token) {


        try {
            Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("JWT 검증 실패: {}", e.getMessage());
            return null; // 또는 특정 예외를 던질 수 있습니다.
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(httpStatus);
        log.info("API GATEWAY ERROR: {}", message); // 에러 로그를 수정했습니다.
        return serverHttpResponse.setComplete();
    }

    static class Config {
        // 설정 관련 필드가 필요한 경우 여기에 추가합니다.
    }
}
