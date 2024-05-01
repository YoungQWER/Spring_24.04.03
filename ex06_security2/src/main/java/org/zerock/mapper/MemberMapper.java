package org.zerock.mapper;

import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;

public interface MemberMapper {

	void insert(MemberVO memberVO);
	
	void insertAuth(AuthVO auto);
	
	public MemberVO read(String userid);
}
