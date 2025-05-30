package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/manifest.json",
                                "/sw.js",
                                "/css/**",
                                "/js/**",
                                "/icons/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // Nếu có form login, bật cái này (hoặc có thể bỏ nếu chỉ dùng API/token):
                .formLogin(form -> form
                        .loginPage("/login") // đổi đường dẫn nếu bạn có UI login
                        .permitAll()
                )
                // Cho phép logout nếu bạn có tính năng này:
                .logout(logout -> logout.permitAll())
                // Đối với PWA/static file nên disable csrf cho đơn giản:
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}

