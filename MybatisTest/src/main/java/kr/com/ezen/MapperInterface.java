
package kr.com.ezen;

import java.util.List;

public interface MapperInterface {

	int insertMember(MemberVO vo);
	int updateMember(MemberVO vo);
	int deleteMember(int id);
	
	MemberVO selectMemberOne(int id);
	List<MemberVO> selectMemeberList();
}
