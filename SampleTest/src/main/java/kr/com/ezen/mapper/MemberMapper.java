package kr.com.ezen.mapper;

import kr.com.ezen.dto.MemberVO;

public interface MemberMapper {
	public String getTime();
	
	public int insertMember(MemberVO vo);
	public int updateMember(MemberVO vo);
	public int deleteMember(int id);
	public MemberVO selectOneMember(int id);
}
