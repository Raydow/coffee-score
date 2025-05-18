package com.raydow.efit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class TrainingHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private LocalDateTime startedAt;

  @Column(nullable = false)
  private LocalDateTime endedAt;

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

  public LocalDateTime getEndedAt() {
    return endedAt;
  }

  public void setEndedAt(LocalDateTime endedAt) {
    this.endedAt = endedAt;
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
    private final TrainingHistory instance = new TrainingHistory();

    public Builder startedAt(LocalDateTime startedAt) {
      instance.startedAt = startedAt;
      return this;
    }

    public Builder endedAt(LocalDateTime endedAt) {
      instance.endedAt = endedAt;
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

    public TrainingHistory build() {
      return instance;
    }
  }
}
