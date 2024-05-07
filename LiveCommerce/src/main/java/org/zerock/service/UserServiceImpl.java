package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.UserVO;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void registerUser(UserVO user) {
        // 사용자 등록 로직 구현
    }

    @Override
    public UserVO getUser(int userId) {
        // 사용자 정보 조회 로직 구현
        return null;
    }

    @Override
    public void updateUser(UserVO user) {
        // 사용자 정보 업데이트 로직 구현
    }

    @Override
    public void deleteUser(int userId) {
        // 사용자 삭제 로직 구현
    }

    @Override
    public boolean loginUser(String username, String password) {
        // 사용자 로그인 로직 구현
        return false;
    }

    @Override
    public List<UserVO> getUserList() {
        // 사용자 목록 조회 로직 구현
        return null;
    }
}
