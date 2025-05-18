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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final TrainingHistoryVO instance = new TrainingHistoryVO();

    public Builder id(Integer id) {
      instance.setId(id);
      return this;
    }

    public Builder startedAt(LocalDateTime startedAt) {
      instance.setStartedAt(startedAt);
      return this;
    }

    public Builder endedAt(LocalDateTime endedAt) {
      instance.setEndedAt(endedAt);
      return this;
    }

    public Builder userId(Integer userId) {
      instance.setUserId(userId);
      return this;
    }

    public Builder trainingTypeId(Integer trainingTypeId) {
      instance.setTrainingTypeId(trainingTypeId);
      return this;
    }

    public TrainingHistoryVO build() {
      return instance;
    }
  }
}
