package com.raydow.efit.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponseVO {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("refresh_token")
  private String refreshToken;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private final AuthenticationResponseVO instance = new AuthenticationResponseVO();

    public Builder accessToken(String accessToken) {
      instance.setAccessToken(accessToken);
      return this;
    }

    public Builder refreshToken(String refreshToken) {
      instance.setRefreshToken(refreshToken);
      return this;
    }

    public AuthenticationResponseVO build() {
      return instance;
    }
  }
}