package com.example.booksmartapp.models.requests;

public class TokenRequest {
    private String refresh_token;
    public TokenRequest(String refresh_token)
    {
        this.refresh_token = refresh_token;
    }
}
