package org.zerock.mapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.UserVO;
import org.zerock.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test  //C
    public void testRegisterUser() {
        UserVO user = UserVO.builder()
        		.UserID(2)
        		.username("test")
        		.email("aaa@naver.com")
        		.password("1234")
        		.shippingAddress("서울시 강남구")
        		.shippingPostalCode("17077")
        		.build();

            log.info(user);
        userMapper.registerUser(user);

      
    }

    @Test //R
    public void testGetUser() {
        
    	 UserVO user = userMapper.getUser(1);
    	
    	 log.info(user);

    }
    @Test //U
    public void testupdateUser() {
    	
    	UserVO user = UserVO.builder()
    			.username("홍길동")
    			.email("bbb@naver.com")
    			.password("4567")
    			.shippingAddress("부산시 해운대구")
    			.shippingPostalCode("12705")
    			.UserID(1)
    			.build();
    	
    	userMapper.updateUser(user);
    	
    }
    @Test //D
	public void testDeleteUser() {
		
    	userMapper.deleteUser(2);	
		
	}
    @Test 
    public void testGetUserList() {
    	// 사용자 전체 리스트 가져오기
    	List<UserVO> userList = userMapper.getUserList();
    	log.info(userList);
    }
    @Test
    public void testLoginUser() {
    	// 올바른 사용자 이름과 비밀번호를 전달하여 로그인을 시도
    	boolean isValidLogin = userMapper.loginUser("validUsername", "validPassword");
    	
    	// 로그인이 성공적으로 이루어졌는지 확인
       log.info(isValidLogin);
    	
    	// 존재하지 않는 사용자 이름과 비밀번호를 전달하여 로그인을 시도
    	boolean isInvalidLogin = userMapper.loginUser("nonExistentUsername", "invalidPassword");
    	
    	// 로그인이 실패했는지 확인
    	log.info(isInvalidLogin);
   
    }
}