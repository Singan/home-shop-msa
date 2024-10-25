package com.example.gateway.filter;

import com.example.gateway.jwt.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final JWTService jwtService;

    @Autowired
    public AuthorizationHeaderFilter(JWTService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
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
            String userId = jwtService.parseToken(jwt);

            if (userId == null) {
                return onError(exchange, "잘못된 토큰입니다.", HttpStatus.UNAUTHORIZED);
            }

            ServerHttpRequest updatedRequest = request.mutate()
                    .header("X-User-Id", userId)
                    .build();

            exchange = exchange.mutate().request(updatedRequest).build();
            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(httpStatus);
        log.info("API GATEWAY ERROR: {}", message);
        return serverHttpResponse.setComplete();
    }

    static class Config {
        // 설정 관련 필드가 필요한 경우 여기에 추가합니다.
    }
}
