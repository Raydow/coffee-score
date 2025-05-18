package com.raydow.efit.service.impl;

import com.raydow.efit.domain.Training;
import com.raydow.efit.domain.User;
import com.raydow.efit.domain.TrainingType;
import com.raydow.efit.domain.TrainingStatus;
import com.raydow.efit.repository.TrainingRepository;
import com.raydow.efit.repository.TrainingTypeRepository;
import com.raydow.efit.service.TrainingHistoryService;
import com.raydow.efit.service.mapper.TrainingMapperVO;
import com.raydow.efit.service.vo.TrainingVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private TrainingMapperVO trainingMapperVO;

    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @Mock
    private TrainingHistoryService trainingHistoryService;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private Training trainingEntity;
    private TrainingVO trainingVO;
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

        trainingEntity = new Training();
        trainingEntity.setId(1);
        trainingEntity.setStartedAt(LocalDateTime.now());
        trainingEntity.setStatus(TrainingStatus.IN_PROGRESS);
        trainingEntity.setUser(userEntity);
        trainingEntity.setTrainingType(trainingTypeEntity);

        trainingVO = new TrainingVO();
        trainingVO.setId(1);
        trainingVO.setStartedAt(trainingEntity.getStartedAt());
        trainingVO.setStatus(TrainingStatus.IN_PROGRESS);
        trainingVO.setUserId(userEntity.getId());
        trainingVO.setTrainingTypeId(trainingTypeEntity.getId());
        trainingVO.setTrainingTypeName("Cardio");
    }

    @Test
    void createTraining_shouldReturnCreatedTrainingVO() {
        when(trainingTypeRepository.findByName("Cardio")).thenReturn(Optional.of(trainingTypeEntity));

        when(trainingMapperVO.toEntity(trainingVO)).thenAnswer(invocation -> {
            TrainingVO vo = invocation.getArgument(0);
            Training training = new Training();
            training.setId(vo.getId());
            training.setStartedAt(vo.getStartedAt());
            training.setStatus(vo.getStatus());
            training.setUser(userEntity);
            training.setTrainingType(trainingTypeEntity);  // importante setar aqui o trainingType mockado
            return training;
        });

        when(trainingRepository.save(any(Training.class))).thenReturn(trainingEntity);
        when(trainingMapperVO.toVO(trainingEntity)).thenReturn(trainingVO);

        TrainingVO result = trainingService.createTraining(trainingVO);

        assertNotNull(result);
        assertEquals(trainingVO.getId(), result.getId());

        verify(trainingTypeRepository).findByName("Cardio");
        verify(trainingMapperVO).toEntity(trainingVO);
        verify(trainingRepository).save(any(Training.class));
        verify(trainingMapperVO).toVO(trainingEntity);
    }

    @Test
    void getTrainingById_found_shouldReturnOptionalVO() {
        when(trainingRepository.findById(1)).thenReturn(Optional.of(trainingEntity));
        when(trainingMapperVO.toVO(trainingEntity)).thenReturn(trainingVO);

        Optional<TrainingVO> result = trainingService.getTrainingById(1);

        assertTrue(result.isPresent());
        assertEquals(trainingVO.getId(), result.get().getId());
        verify(trainingRepository).findById(1);
        verify(trainingMapperVO).toVO(trainingEntity);
    }

    @Test
    void getTrainingById_notFound_shouldReturnEmptyOptional() {
        when(trainingRepository.findById(1)).thenReturn(Optional.empty());

        Optional<TrainingVO> result = trainingService.getTrainingById(1);

        assertFalse(result.isPresent());
        verify(trainingRepository).findById(1);
        verifyNoMoreInteractions(trainingMapperVO);
    }

    @Test
    void getAllTrainings_shouldReturnListOfVOs() {
        List<Training> entities = new ArrayList<>();
        entities.add(trainingEntity);
        when(trainingRepository.findAll()).thenReturn(entities);
        when(trainingMapperVO.toVO(trainingEntity)).thenReturn(trainingVO);

        List<TrainingVO> result = trainingService.getAllTrainings();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(trainingVO.getId(), result.get(0).getId());
        verify(trainingRepository).findAll();
        verify(trainingMapperVO).toVO(trainingEntity);
    }

    @Test
    void updateTraining_existing_shouldUpdateAndReturnVO() {
        when(trainingRepository.findById(1)).thenReturn(Optional.of(trainingEntity));
        doNothing().when(trainingMapperVO).updateEntity(trainingVO, trainingEntity);
        when(trainingRepository.save(trainingEntity)).thenReturn(trainingEntity);
        when(trainingMapperVO.toVO(trainingEntity)).thenReturn(trainingVO);

        TrainingVO result = trainingService.updateTraining(1, trainingVO);

        assertNotNull(result);
        assertEquals(trainingVO.getId(), result.getId());
        verify(trainingRepository).findById(1);
        verify(trainingMapperVO).updateEntity(trainingVO, trainingEntity);
        verify(trainingRepository).save(trainingEntity);
        verify(trainingMapperVO).toVO(trainingEntity);
    }

    @Test
    void updateTraining_notFound_shouldThrowException() {
        when(trainingRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> trainingService.updateTraining(1, trainingVO));

        assertEquals("Training not found with id 1", thrown.getMessage());
        verify(trainingRepository).findById(1);
        verifyNoMoreInteractions(trainingMapperVO);
    }

    @Test
    void deleteTraining_existing_shouldDelete() {
        when(trainingRepository.existsById(1)).thenReturn(true);
        doNothing().when(trainingRepository).deleteById(1);

        trainingService.deleteTraining(1);

        verify(trainingRepository).existsById(1);
        verify(trainingRepository).deleteById(1);
    }

    @Test
    void deleteTraining_notFound_shouldThrowException() {
        when(trainingRepository.existsById(1)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> trainingService.deleteTraining(1));

        assertEquals("Training not found with id 1", thrown.getMessage());
        verify(trainingRepository).existsById(1);
        verifyNoMoreInteractions(trainingRepository);
    }
}
