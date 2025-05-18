package com.raydow.efit.api.v1.controller;

import com.raydow.efit.api.v1.dto.user.UserCreateDTO;
import com.raydow.efit.api.v1.dto.user.UserResponseDTO;
import com.raydow.efit.api.v1.dto.user.UserUpdateDTO;
import com.raydow.efit.api.v1.mapper.UserMapperDTO;
import com.raydow.efit.service.UserService;
import com.raydow.efit.service.vo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.raydow.efit.api.v1.controller.RestPath.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH + "/users")
public class UserController {

    private final UserService userService;
    private final UserMapperDTO userMapperDTO;

    public UserController(UserService userService, UserMapperDTO userMapperDTO) {
        this.userService = userService;
        this.userMapperDTO = userMapperDTO;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO createDTO) {
        UserVO userVO = userMapperDTO.fromCreateDtoToVO(createDTO);
        UserVO created = userService.createUser(userVO);
        UserResponseDTO responseDTO = userMapperDTO.toResponseDTO(created);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(userVO -> new ResponseEntity<>(userMapperDTO.toResponseDTO(userVO), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> list = userService.getAllUsers().stream()
                .map(userMapperDTO::toResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO updateDTO) {
        UserVO userVO = userMapperDTO.fromUpdateDtoToVO(updateDTO);
        UserVO updated = userService.updateUser(id, userVO);
        UserResponseDTO responseDTO = userMapperDTO.toResponseDTO(updated);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
