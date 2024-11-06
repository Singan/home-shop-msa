package com.example.service.config.filter;

import feign.RequestInterceptor;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static String getToken() {
        return tokenHolder.get();
    }

    private void clearToken() {
        tokenHolder.remove();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        if (token != null && !token.isEmpty()) {
            tokenHolder.set(token);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            clearToken();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Bean
    public RequestInterceptor requestTokenInterceptor() {
        // 다른 API 로 요청할 때 API 게이트웨이에서 받은 토큰을 그대로 넘겨준다.
        return requestTemplate -> {
            String token = TokenFilter.getToken();
            if (token != null) {
                requestTemplate.header("Authorization", token);
            }
        };
    }
}
