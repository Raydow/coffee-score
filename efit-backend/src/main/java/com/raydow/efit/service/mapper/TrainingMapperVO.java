package com.raydow.efit.service.mapper;

import com.raydow.efit.domain.Training;
import com.raydow.efit.service.vo.TrainingVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapperVO {

    private final ModelMapper modelMapper;

    public TrainingMapperVO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TrainingVO toVO(Training entity) {
        return modelMapper.map(entity, TrainingVO.class);
    }

    public Training toEntity(TrainingVO vo) {
        return modelMapper.map(vo, Training.class);
    }

    public void updateEntity(TrainingVO vo, Training entity) {
        modelMapper.map(vo, entity);
    }
}
