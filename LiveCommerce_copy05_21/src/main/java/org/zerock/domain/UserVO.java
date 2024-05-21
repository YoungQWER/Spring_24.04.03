package org.zerock.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserVO implements UserDetails {
    private int userID; // 사용자 ID
    private String username; // 사용자 이름
    private String email; // 사용자 이메일
    private String password; // 사용자 비밀번호
    private String shippingAddress; // 배송 주소
    private String shippingPostalCode; // 배송 우편번호
    private int enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 나타내는 SimpleGrantedAuthority 객체를 반환합니다.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public String getPassword() {
      return password;
   }
    
    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠기지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았음을 반환합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되었음을 반환합니다.
        return true;
    }
}