package com.example.service.config;

import com.example.service.config.filter.TokenFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter());

        registrationBean.addUrlPatterns("/*");

        registrationBean.setOrder(10);

        return registrationBean;
    }
}