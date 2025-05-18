package com.raydow.efit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Token {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String token;

  @Enumerated(EnumType.STRING)
  private TokenType tokenType = TokenType.BEARER;

  private boolean revoked;

  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private com.raydow.efit.domain.User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenType tokenType) {
    this.tokenType = tokenType;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final Token instance = new Token();

    public Builder token(String token) {
      instance.setToken(token);
      return this;
    }

    public Builder tokenType(TokenType tokenType) {
      instance.setTokenType(tokenType);
      return this;
    }

    public Builder revoked(boolean revoked) {
      instance.setRevoked(revoked);
      return this;
    }

    public Builder expired(boolean expired) {
      instance.setExpired(expired);
      return this;
    }

    public Builder user(User user) {
      instance.setUser(user);
      return this;
    }

    public Token build() {
      return instance;
    }
  }
}