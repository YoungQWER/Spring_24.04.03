package kr.com.ezen.service;

import java.util.List;

import kr.com.ezen.dto.MemberVO;

public interface MemberService {
	
	public void insertMember(MemberVO vo);
	public void updateMember(MemberVO vo);
	public void deleteMember(int id);
	
	public MemberVO selectOne(int id);
	public List<MemberVO> list();

}
