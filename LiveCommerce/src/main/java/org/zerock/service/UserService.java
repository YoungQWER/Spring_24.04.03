package org.zerock.service;

import java.util.List;

import org.zerock.domain.UserVO;

public interface UserService {

    void registerUser(UserVO user);

    UserVO getUser(int userId);

    void updateUser(UserVO user);

    void deleteUser(int userId);

    boolean loginUser(String username, String password);

    List<UserVO> getUserList();
    
    UserVO selectUserByUserName(String username);
}