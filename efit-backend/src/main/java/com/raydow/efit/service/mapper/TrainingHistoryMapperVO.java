package com.raydow.efit.service.mapper;

import com.raydow.efit.domain.TrainingHistory;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingHistoryMapperVO {

    private final ModelMapper modelMapper;

    public TrainingHistoryMapperVO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TrainingHistoryVO toVO(TrainingHistory entity) {
        return modelMapper.map(entity, TrainingHistoryVO.class);
    }

    public TrainingHistory toEntity(TrainingHistoryVO vo) {
        return modelMapper.map(vo, TrainingHistory.class);
    }

    public void updateEntity(TrainingHistoryVO vo, TrainingHistory entity) {
        modelMapper.map(vo, entity);
    }
}
