package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.UserVO;
import org.zerock.mapper.ProductMapper;
import org.zerock.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;

	@Override
	public void registerUser(UserVO user) {
		userMapper.registerUser(user);
		
	}

	@Override
	public UserVO getUser(int userId) {
		return userMapper.getUser(userId);
	}

	@Override
	public void updateUser(UserVO user) {
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		userMapper.deleteUser(userId);
		
	}

	@Override
	public boolean loginUser(String username, String password) {
	    // 사용자 정보를 데이터베이스에서 조회
	    UserVO user = userMapper.selectUserByUserName(username); // 여기는 메서드명을 조금 수정해야 할 수도 있습니다. 
	    
	    // 사용자 정보가 없으면 로그인 실패
	    if (user == null) {
	        return false;
	    }
	    
	    // 입력된 비밀번호와 데이터베이스의 비밀번호가 일치하는지 확인
	    // 여기서는 간단하게 비밀번호만 확인하는 것으로 가정합니다. 실제로는 보안상의 이유로 비밀번호를 암호화하여 저장하고, 비교해야 합니다.
	    if (password.equals(user.getPassword())) {
	        return true; // 비밀번호가 일치하면 로그인 성공
	    } else {
	        return false; // 비밀번호가 일치하지 않으면 로그인 실패
	    }
	}

	@Override
	public List<UserVO> getUserList() {
		
		return userMapper.getUserList();
	}

	@Override
	public UserVO selectUserByUserName(String username) {
		
		return userMapper.selectUserByUserName(username);
	}


}