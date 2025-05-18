package com.raydow.efit.service.vo;

import com.raydow.efit.domain.TrainingStatus;
import java.time.LocalDateTime;

public class TrainingVO {

  private Integer id;
  private LocalDateTime startedAt;
  private TrainingStatus status;
  private Integer userId;
  private Integer trainingTypeId;
  private String trainingTypeName;

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

  public TrainingStatus getStatus() {
    return status;
  }

  public void setStatus(TrainingStatus status) {
    this.status = status;
  }

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

  public String getTrainingTypeName() {
    return trainingTypeName;
  }

  public void setTrainingTypeName(String trainingTypeName) {
    this.trainingTypeName = trainingTypeName;
  }
}
