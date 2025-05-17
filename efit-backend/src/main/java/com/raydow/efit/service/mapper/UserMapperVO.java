package com.raydow.efit.service.mapper;

import com.raydow.efit.domain.User;
import com.raydow.efit.service.vo.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperVO {

    private final ModelMapper modelMapper;

    public UserMapperVO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserVO toVO(User entity) {
        return modelMapper.map(entity, UserVO.class);
    }

    public User toEntity(UserVO vo) {
        return modelMapper.map(vo, User.class);
    }

    public void updateEntity(UserVO vo, User entity) {
        modelMapper.map(vo, entity);
    }
}
