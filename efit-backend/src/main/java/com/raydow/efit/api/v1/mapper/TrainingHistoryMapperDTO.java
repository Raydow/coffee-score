package com.raydow.efit.api.v1.mapper;

import com.raydow.efit.api.v1.dto.TrainingHistoryResponseDTO;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingHistoryMapperDTO {

    private final ModelMapper modelMapper;

    public TrainingHistoryMapperDTO() {
        this.modelMapper = new ModelMapper();
    }

    public TrainingHistoryResponseDTO toResponseDTO(TrainingHistoryVO vo) {
        return modelMapper.map(vo, TrainingHistoryResponseDTO.class);
    }
}
