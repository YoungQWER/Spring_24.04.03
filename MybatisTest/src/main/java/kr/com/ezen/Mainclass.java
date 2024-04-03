package kr.com.ezen;

import java.io.InputStream;

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

			System.out.println(sqlSessionFactory);
			
			SqlSession session = sqlSessionFactory.openSession(true);
			
			Mapperinterface mapper = session.getMapper(Mapperinterface.class);
			
			System.out.println("session : " + session);
			
			MemberVO vo = new MemberVO();
			
			vo.setId(3);
			vo.setName("조조");
			vo.setPhone("010-1111-2222");
			vo.setAddress("경기도 수원시");

			int result = mapper.insertMember(vo);
			System.out.println("성공이면 :" + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
