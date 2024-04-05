package kr.com.ezen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.junit.Test;

import kr.com.ezen.dto.MemberVO;

public interface MemberMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	public String getTime2();
	
	public int insertMember(MemberVO vo);
	
	public int updateMember(MemberVO vo);
	
	public int deleteMember(int id);
	
	public MemberVO selectOneMember(int id);	
	
	public List<MemberVO> selectAllmember();
	
}
