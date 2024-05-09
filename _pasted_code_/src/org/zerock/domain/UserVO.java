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
    private int userID; // ����� ID
    private String username; // ����� �̸�
    private String email; // ����� �̸���
    private String password; // ����� ��й�ȣ
    private String shippingAddress; // ��� �ּ�
    private String shippingPostalCode; // ��� �����ȣ

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ������� ������ ��Ÿ���� SimpleGrantedAuthority ��ü�� ��ȯ�մϴ�.
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
        // ������ ������� �ʾ����� ��ȯ�մϴ�.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // ������ ����� �ʾ����� ��ȯ�մϴ�.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // �ڰ� ������ ������� �ʾ����� ��ȯ�մϴ�.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ������ Ȱ��ȭ�Ǿ����� ��ȯ�մϴ�.
        return true;
    }
}
