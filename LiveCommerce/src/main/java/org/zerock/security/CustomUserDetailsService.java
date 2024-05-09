package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.domain.UserVO;
import org.zerock.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper mapper;
	
	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("------------------------------------------");
		log.info(username);
		log.info("------------------------------------------");
		
		UserVO vo = mapper.selectUserByUserName(username);
		
		log.info(vo);
		
		return vo;
	}

}
