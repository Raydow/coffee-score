package com.raydow.efit.api.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TrainingUpdateDTO {

    private Integer userId;
    private Integer trainingTypeId;

    @Schema(
            description = "Training status",
            allowableValues = {"IN_PROGRESS", "PAUSED", "FINISHED"},
            example = "IN_PROGRESS"
    )
    private String status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(Integer trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
