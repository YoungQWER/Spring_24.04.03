package org.zerock.security;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/user/register").permitAll() // 회원가입 페이지에 대한 접근은 모두 허용
                .anyRequest().authenticated() // 그 외의 모든 요청은 인증되어야 함
                .and()
            .formLogin()
                .loginPage("/user/login") // 로그인 페이지 지정
                .permitAll() // 로그인 페이지에 대한 접근은 모두 허용
                .and()
            .logout()
                .logoutUrl("/user/logout") // 로그아웃 URL 지정
                .logoutSuccessUrl("/live/main") // 로그아웃 성공 시 이동할 페이지 지정
                .permitAll(); // 로그아웃에 대한 접근은 모두 허용
    }
}