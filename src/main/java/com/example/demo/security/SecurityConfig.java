//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/manifest.json",
//                                "/sw.js",
//                                "/icons/**",
//                                "/css/**",
//                                "/js/**",
//                                "/images/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}
