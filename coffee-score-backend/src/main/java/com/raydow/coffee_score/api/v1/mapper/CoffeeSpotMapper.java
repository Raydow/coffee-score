package com.raydow.coffee_score.api.v1.mapper;

import com.raydow.coffee_score.api.v1.dto.CoffeeSpotCreateDTO;
import com.raydow.coffee_score.api.v1.dto.CoffeeSpotDTO;
import com.raydow.coffee_score.domain.CoffeeSpot;
import com.raydow.coffee_score.service.vo.CoffeeSpotVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CoffeeSpotMapper {

    private final ModelMapper mapper;

    public CoffeeSpotMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CoffeeSpotDTO toDTO(CoffeeSpotVO vo) {
        return mapper.map(vo, CoffeeSpotDTO.class);
    }

    public CoffeeSpotVO toVO(CoffeeSpotDTO dto) {
        return mapper.map(dto, CoffeeSpotVO.class);
    }

    public CoffeeSpotVO toVO(CoffeeSpotCreateDTO createDTO) {
        return mapper.map(createDTO, CoffeeSpotVO.class);
    }

    public CoffeeSpot toEntity(CoffeeSpotVO vo) {
        return mapper.map(vo, CoffeeSpot.class);
    }

    public CoffeeSpotVO toVO(CoffeeSpot entity) {
        return mapper.map(entity, CoffeeSpotVO.class);
    }
}
