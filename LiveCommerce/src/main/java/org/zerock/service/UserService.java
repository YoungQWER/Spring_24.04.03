package org.zerock.service;

import java.util.List;

import org.zerock.domain.UserVO;

public interface UserService {

    public void registerUser(UserVO user);

    public UserVO getUser(int userId);

    public void updateUser(UserVO user);

    public void deleteUser(int userId);

    public boolean loginUser(String username, String password);

    public List<UserVO> getUserList();
}
