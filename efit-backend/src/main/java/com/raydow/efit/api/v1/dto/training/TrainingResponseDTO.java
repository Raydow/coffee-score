package com.raydow.efit.api.v1.dto.training;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class TrainingResponseDTO {

    private Integer id;

    private LocalDateTime startedAt;

    @Schema(description = "Training status", allowableValues = {"IN_PROGRESS", "PAUSED", "FINISHED"})
    private String status;

    private String trainingTypeName;

    public Integer userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }
}
