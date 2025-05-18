package com.raydow.efit.service.impl;

import com.raydow.efit.domain.TrainingHistory;
import com.raydow.efit.domain.TrainingType;
import com.raydow.efit.domain.User;
import com.raydow.efit.repository.TrainingHistoryRepository;
import com.raydow.efit.service.mapper.TrainingHistoryMapperVO;
import com.raydow.efit.service.vo.TrainingHistoryVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TrainingHistoryServiceImplTest {

    @Mock
    private TrainingHistoryRepository trainingHistoryRepository;

    @Mock
    private TrainingHistoryMapperVO trainingHistoryMapperVO;

    @InjectMocks
    private TrainingHistoryServiceImpl trainingHistoryService;

    private TrainingHistory trainingHistoryEntity;
    private TrainingHistoryVO trainingHistoryVO;
    private User userEntity;
    private TrainingType trainingTypeEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userEntity = new User();
        userEntity.setId(10);
        userEntity.setName("John Doe");

        trainingTypeEntity = new TrainingType();
        trainingTypeEntity.setId(5);
        trainingTypeEntity.setName("Cardio");

        trainingHistoryEntity = TrainingHistory.builder()
                .startedAt(LocalDateTime.now().minusHours(1))
                .endedAt(LocalDateTime.now())
                .user(userEntity)
                .trainingType(trainingTypeEntity)
                .build();
        trainingHistoryEntity.setId(1);

        trainingHistoryVO = new TrainingHistoryVO();
        trainingHistoryVO.setId(1);
        trainingHistoryVO.setStartedAt(trainingHistoryEntity.getStartedAt());
        trainingHistoryVO.setEndedAt(trainingHistoryEntity.getEndedAt());
        trainingHistoryVO.setUserId(userEntity.getId());
        trainingHistoryVO.setTrainingTypeId(trainingTypeEntity.getId());
    }

    @Test
    void createTrainingHistory_shouldReturnCreatedVO() {
        when(trainingHistoryMapperVO.toEntity(trainingHistoryVO)).thenReturn(trainingHistoryEntity);
        when(trainingHistoryRepository.save(trainingHistoryEntity)).thenReturn(trainingHistoryEntity);
        when(trainingHistoryMapperVO.toVO(trainingHistoryEntity)).thenReturn(trainingHistoryVO);

        TrainingHistoryVO result = trainingHistoryService.createTrainingHistory(trainingHistoryVO);

        assertNotNull(result);
        assertEquals(trainingHistoryVO.getId(), result.getId());
        verify(trainingHistoryMapperVO).toEntity(trainingHistoryVO);
        verify(trainingHistoryRepository).save(trainingHistoryEntity);
        verify(trainingHistoryMapperVO).toVO(trainingHistoryEntity);
    }

    @Test
    void getTrainingHistoryById_found_shouldReturnOptionalVO() {
        when(trainingHistoryRepository.findById(1)).thenReturn(Optional.of(trainingHistoryEntity));
        when(trainingHistoryMapperVO.toVO(trainingHistoryEntity)).thenReturn(trainingHistoryVO);

        Optional<TrainingHistoryVO> result = trainingHistoryService.getTrainingHistoryById(1);

        assertTrue(result.isPresent());
        assertEquals(trainingHistoryVO.getId(), result.get().getId());
        verify(trainingHistoryRepository).findById(1);
        verify(trainingHistoryMapperVO).toVO(trainingHistoryEntity);
    }

    @Test
    void getTrainingHistoryById_notFound_shouldReturnEmptyOptional() {
        when(trainingHistoryRepository.findById(1)).thenReturn(Optional.empty());

        Optional<TrainingHistoryVO> result = trainingHistoryService.getTrainingHistoryById(1);

        assertFalse(result.isPresent());
        verify(trainingHistoryRepository).findById(1);
        verifyNoMoreInteractions(trainingHistoryMapperVO);
    }

    @Test
    void getAllTrainingHistories_shouldReturnListOfVOs() {
        List<TrainingHistory> entities = new ArrayList<>();
        entities.add(trainingHistoryEntity);

        when(trainingHistoryRepository.findAll()).thenReturn(entities);
        when(trainingHistoryMapperVO.toVO(trainingHistoryEntity)).thenReturn(trainingHistoryVO);

        List<TrainingHistoryVO> result = trainingHistoryService.getAllTrainingHistories();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(trainingHistoryVO.getId(), result.get(0).getId());
        verify(trainingHistoryRepository).findAll();
        verify(trainingHistoryMapperVO).toVO(trainingHistoryEntity);
    }

    @Test
    void updateTrainingHistory_existing_shouldUpdateAndReturnVO() {
        when(trainingHistoryRepository.findById(1)).thenReturn(Optional.of(trainingHistoryEntity));
        doNothing().when(trainingHistoryMapperVO).updateEntity(trainingHistoryVO, trainingHistoryEntity);
        when(trainingHistoryRepository.save(trainingHistoryEntity)).thenReturn(trainingHistoryEntity);
        when(trainingHistoryMapperVO.toVO(trainingHistoryEntity)).thenReturn(trainingHistoryVO);

        TrainingHistoryVO result = trainingHistoryService.updateTrainingHistory(1, trainingHistoryVO);

        assertNotNull(result);
        assertEquals(trainingHistoryVO.getId(), result.getId());
        verify(trainingHistoryRepository).findById(1);
        verify(trainingHistoryMapperVO).updateEntity(trainingHistoryVO, trainingHistoryEntity);
        verify(trainingHistoryRepository).save(trainingHistoryEntity);
        verify(trainingHistoryMapperVO).toVO(trainingHistoryEntity);
    }

    @Test
    void updateTrainingHistory_notFound_shouldThrowException() {
        when(trainingHistoryRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> trainingHistoryService.updateTrainingHistory(1, trainingHistoryVO));

        assertEquals("TrainingHistory not found with id 1", thrown.getMessage());
        verify(trainingHistoryRepository).findById(1);
        verifyNoMoreInteractions(trainingHistoryMapperVO);
    }

    @Test
    void deleteTrainingHistory_existing_shouldDelete() {
        when(trainingHistoryRepository.existsById(1)).thenReturn(true);
        doNothing().when(trainingHistoryRepository).deleteById(1);

        trainingHistoryService.deleteTrainingHistory(1);

        verify(trainingHistoryRepository).existsById(1);
        verify(trainingHistoryRepository).deleteById(1);
    }

    @Test
    void deleteTrainingHistory_notFound_shouldThrowException() {
        when(trainingHistoryRepository.existsById(1)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> trainingHistoryService.deleteTrainingHistory(1));

        assertEquals("TrainingHistory not found with id 1", thrown.getMessage());
        verify(trainingHistoryRepository).existsById(1);
        verifyNoMoreInteractions(trainingHistoryRepository);
    }
}
