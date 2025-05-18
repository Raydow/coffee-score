package com.raydow.efit.api.v1.dto.training;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class TrainingCreateDTO {

    @NotNull
    private Integer userId;

    @NotBlank
    private String trainingTypeName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrainingTypeName() {
        return trainingTypeName;
    }

    public void setTrainingTypeName(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
