package com.raydow.efit.service.impl;

import com.raydow.efit.domain.User;
import com.raydow.efit.repository.UserRepository;
import com.raydow.efit.service.UserService;
import com.raydow.efit.service.mapper.UserMapperVO;
import com.raydow.efit.service.vo.UserVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapperVO userMapperVO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapperVO userMapperVO, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapperVO = userMapperVO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserVO createUser(UserVO userVO) {
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        User user = userMapperVO.toEntity(userVO);
        user = userRepository.save(user);
        return userMapperVO.toVO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserVO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapperVO::toVO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserVO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapperVO::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public UserVO updateUser(Long id, UserVO userVO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        userMapperVO.updateEntity(userVO, user);

        user = userRepository.save(user);
        return userMapperVO.toVO(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
