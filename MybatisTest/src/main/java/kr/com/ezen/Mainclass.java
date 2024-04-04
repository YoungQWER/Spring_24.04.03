package kr.com.ezen;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Mainclass {

	public static void main(String[] args) {
		
		try {
			String resource = "kr/com/ezen/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
						
			SqlSession session = sqlSessionFactory.openSession(true);
			
			MapperInterface mapper = session.getMapper(MapperInterface.class);		
			
			System.out.println("session  : " +  session);
			
			MemberVO vo = new MemberVO();
			
			vo.setId(5);
			vo.setName("라마바사");
			vo.setPhone("010-1234-5678");
			vo.setAddress("주소");
			
//			mapper.insertMember(vo);
			
//			mapper.deleteMember(1);
			
//			int result = mapper.insertMember(vo);
//			System.out.println("성공이면 " + result);
			
//			vo = mapper.selectMemberOne(4);
			
			List<MemberVO> list = mapper.selectMemeberList();
			
			for(MemberVO v2 : list)
				System.out.println(v2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}