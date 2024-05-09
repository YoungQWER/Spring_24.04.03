package org.zerock.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            // 사용자 인증을 위한 메모리 기반 인증을 설정합니다.
            .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER") // 패스워드는 평문으로 저장됩니다. 실제 프로젝트에서는 패스워드를 안전하게 저장해야 합니다.
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN"); // ADMIN 권한을 가진 사용자를 추가합니다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // HTTP 보안을 구성합니다.
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한이 필요한 경로 설정
                .anyRequest().authenticated() // 모든 요청은 인증되어야 합니다.
                .and()
            .formLogin()
                .loginPage("/login") // 로그인 페이지 설정
                .permitAll() // 로그인 페이지는 모든 사용자에게 허용됩니다.
                .and()
            .logout()
                .logoutUrl("/logout") // 로그아웃 URL 설정
                .permitAll(); // 로그아웃은 모든 사용자에게 허용됩니다.
    }
}
