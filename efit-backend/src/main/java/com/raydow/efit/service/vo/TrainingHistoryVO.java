package com.raydow.efit.service.vo;

import java.time.LocalDateTime;

public class TrainingHistoryVO {

  private Integer id;
  private LocalDateTime startedAt;
  private LocalDateTime endedAt;
  private Integer userId;
  private Integer trainingTypeId;

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

  public LocalDateTime getEndedAt() {
    return endedAt;
  }

  public void setEndedAt(LocalDateTime endedAt) {
    this.endedAt = endedAt;
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
}
