package com.raydow.efit.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Training {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private LocalDateTime startedAt;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TrainingStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "training_type_id", nullable = false)
  private TrainingType trainingType;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TrainingType getTrainingType() {
    return trainingType;
  }

  public void setTrainingType(TrainingType trainingType) {
    this.trainingType = trainingType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final Training instance = new Training();

    public Builder startedAt(LocalDateTime startedAt) {
      instance.startedAt = startedAt;
      return this;
    }

    public Builder status(TrainingStatus status) {
      instance.status = status;
      return this;
    }

    public Builder user(User user) {
      instance.user = user;
      return this;
    }

    public Builder trainingType(TrainingType trainingType) {
      instance.trainingType = trainingType;
      return this;
    }

    public Training build() {
      return instance;
    }
  }
}
