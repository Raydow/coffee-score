package com.raydow.efit.api.v1.mapper;

import com.raydow.efit.api.v1.dto.UserCreateDTO;
import com.raydow.efit.api.v1.dto.UserResponseDTO;
import com.raydow.efit.api.v1.dto.UserUpdateDTO;
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
        return modelMapper.map(dto, UserVO.class);
    }

    public UserVO fromUpdateDtoToVO(UserUpdateDTO dto) {
        return modelMapper.map(dto, UserVO.class);
    }

    public UserResponseDTO toResponseDTO(UserVO vo) {
        return modelMapper.map(vo, UserResponseDTO.class);
    }
}
