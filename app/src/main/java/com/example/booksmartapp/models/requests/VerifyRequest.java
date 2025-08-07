package com.example.booksmartapp.models.requests;

public class VerifyRequest {
    private String email_token;

    public VerifyRequest(String email_token) {
        this.email_token = email_token;
    }
}
