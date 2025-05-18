package com.raydow.efit.api.v1.mapper;

import com.raydow.efit.api.v1.dto.training.TrainingCreateDTO;
import com.raydow.efit.api.v1.dto.training.TrainingResponseDTO;
import com.raydow.efit.service.vo.TrainingVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Component
public class TrainingMapperDTO {

    private final ModelMapper modelMapper;

    public TrainingMapperDTO() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(STRICT);
    }

    public TrainingVO fromCreateDtoToVO(TrainingCreateDTO dto) {
        return modelMapper.map(dto, TrainingVO.class);
    }

    public TrainingResponseDTO toResponseDTO(TrainingVO vo) {
        return modelMapper.map(vo, TrainingResponseDTO.class);
    }
}
