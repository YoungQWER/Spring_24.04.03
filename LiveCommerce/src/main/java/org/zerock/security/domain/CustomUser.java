package org.zerock.security.domain;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.zerock.domain.UserVO;

import lombok.Getter;


@Getter
public class CustomUser implements UserDetails{

	@Autowired
	private UserVO UserVO;
	
//	public CustomUser(String username, String password, Collection<E>) {
//		
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

/*
 * public class CustomUser extends User{
 * 
 * public CustomUser(String username, String password, Collection<? extends
 * GrantedAuthority> authorities) { super(username, password, authorities); //
 * TODO Auto-generated constructor stub }
 * 
 * }
 */
