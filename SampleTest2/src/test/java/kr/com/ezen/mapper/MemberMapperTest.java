package kr.com.ezen.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.com.ezen.dto.MemberVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTest {

	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testInsert() {
		
		MemberVO vo = MemberVO.builder()
				.id(100)
				.name("강조")
				.phone("010-9999-1111")
				.address("수원시 팔달구")
				.build();
		mapper.insertMember(vo);
	}
	
	@Test
	public void testUpdate() {
		
		MemberVO vo = MemberVO.builder()
				.id(100)
				.name("이름")
				.phone("010-1111-2222")
				.address("수원시")
				.build();
		mapper.updateMember(vo);
	}
	
	@Test
	public void testDelete() {
		mapper.deleteMember(100);
	}
	
	@Test
	public void testSelectOne() {
		mapper.selectOneMember(18);
	}
	
	@Test
	public void testSelectList() {
		
		mapper.selectAllmember()
			.forEach(vo -> log.info(vo));
	}
}
