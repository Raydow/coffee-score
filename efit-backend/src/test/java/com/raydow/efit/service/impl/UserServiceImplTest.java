package com.raydow.efit.service.impl;

import com.raydow.efit.domain.User;
import com.raydow.efit.repository.UserRepository;
import com.raydow.efit.service.mapper.UserMapperVO;
import com.raydow.efit.service.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapperVO userMapperVO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserVO userVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("123456");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setGender("M");
        user.setActive(true);
        user.setHeight(1.80);
        user.setWeight(75.0);

        userVO = new UserVO();
        userVO.setId(1L);
        userVO.setName("John Doe");
        userVO.setEmail("john@example.com");
        userVO.setPassword("123456");
        userVO.setBirthDate(LocalDate.of(1990, 1, 1));
        userVO.setGender("M");
        userVO.setActive(true);
        userVO.setHeight(1.80);
        userVO.setWeight(75.0);
    }

    @Test
    void createUser_ShouldReturnCreatedUserVO() {
        when(userMapperVO.toEntity(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        when(userMapperVO.toVO(any())).thenReturn(userVO);

        UserVO result = userService.createUser(userVO);

        assertNotNull(result);
        assertEquals(userVO.getEmail(), result.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void getUserById_ShouldReturnUserVO_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapperVO.toVO(user)).thenReturn(userVO);

        Optional<UserVO> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(userVO.getEmail(), result.get().getEmail());
    }

    @Test
    void getUserById_ShouldReturnEmpty_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<UserVO> result = userService.getUserById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserVOs() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapperVO.toVO(user)).thenReturn(userVO);

        List<UserVO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(userVO.getEmail(), result.get(0).getEmail());
    }

    @Test
    void updateUser_ShouldUpdateAndReturnUserVO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doAnswer(invocation -> {
            UserVO vo = invocation.getArgument(0);
            User u = invocation.getArgument(1);
            u.setName(vo.getName());
            return null;
        }).when(userMapperVO).updateEntity(userVO, user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapperVO.toVO(user)).thenReturn(userVO);

        UserVO result = userService.updateUser(1L, userVO);

        assertEquals(userVO.getName(), result.getName());
    }

    @Test
    void updateUser_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUser(1L, userVO));
    }

    @Test
    void deleteUser_ShouldCallRepositoryDelete() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));
    }
}
