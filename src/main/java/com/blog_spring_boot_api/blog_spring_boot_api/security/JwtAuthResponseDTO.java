package com.blog_spring_boot_api.blog_spring_boot_api.security;

public class JwtAuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
