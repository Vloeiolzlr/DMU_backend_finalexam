package com.dmubackend.spring.security.config;

import com.dmubackend.spring.security.custom.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;


    @Bean // 정적 리소스에 대해 시큐리티 기능 비활성화
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/css/**"),
                        new AntPathRequestMatcher("/js/**"),
                        new AntPathRequestMatcher("/images/**"),
                        new AntPathRequestMatcher("/fonts/**")
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        new AntPathRequestMatcher("/login"),
                        new AntPathRequestMatcher("/breads"),
                        new AntPathRequestMatcher("/order"),
                        new AntPathRequestMatcher("/"),
                        new AntPathRequestMatcher("/trial"),
                        new AntPathRequestMatcher("/public/post/**")
                ).permitAll()
                .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        )
                .csrf(AbstractHttpConfigurer::disable)
                .build();

        // requestMatcher() -> 특정 요청과 일치하는 url에 대한 엑세스 설정
        // permitAll() -> 누구나 접근하게 설정, /login, /profile, /order에 대해 인증, 인가 없이 접근 가능
        // anyRequest() -> 위에서 설정한 url 이외의 요청
        // authenticated() -> 별도의 인가는 필요하지 않지만 인증이 성공된 상태에서 접근 가능
        // loginPage() -> 로그인 페이지 경로 설정
        // invalidateHttpSession -> 로그아웃 이후에 세션 삭제 여부
        // csrf(AbstractHttpConfigurer::disable) -> csrf 설정 비활성화
    }

    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}