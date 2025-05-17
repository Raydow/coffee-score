package com.raydow.efit.service;

import com.raydow.efit.service.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserVO createUser(UserVO userVO);
    Optional<UserVO> getUserById(Long id);
    List<UserVO> getAllUsers();
    UserVO updateUser(Long id, UserVO userVO);
    void deleteUser(Long id);
}
