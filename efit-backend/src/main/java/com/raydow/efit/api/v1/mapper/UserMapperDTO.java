package com.raydow.efit.api.v1.mapper;

import com.raydow.efit.api.v1.dto.user.UserCreateDTO;
import com.raydow.efit.api.v1.dto.user.UserResponseDTO;
import com.raydow.efit.api.v1.dto.user.UserUpdateDTO;
import com.raydow.efit.domain.UserRole;
import com.raydow.efit.service.vo.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO {

    private final ModelMapper modelMapper;

    public UserMapperDTO() {
        this.modelMapper = new ModelMapper();
    }

    public UserVO fromCreateDtoToVO(UserCreateDTO dto) {
        UserVO vo = modelMapper.map(dto, UserVO.class);

        if (dto.getUserRole() != null) {
            try {
                vo.setUserRole(UserRole.valueOf(dto.getUserRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid userRole: " + dto.getUserRole());
            }
        }
        return vo;
    }

    public UserVO fromUpdateDtoToVO(UserUpdateDTO dto) {
        UserVO vo = modelMapper.map(dto, UserVO.class);

        if (dto.getUserRole() != null) {
            try {
                vo.setUserRole(UserRole.valueOf(dto.getUserRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid userRole: " + dto.getUserRole());
            }
        }
        return vo;
    }

    public UserResponseDTO toResponseDTO(UserVO vo) {
        return modelMapper.map(vo, UserResponseDTO.class);
    }
}
