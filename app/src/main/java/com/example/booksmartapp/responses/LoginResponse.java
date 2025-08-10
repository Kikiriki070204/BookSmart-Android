package com.example.booksmartapp.responses;

import com.example.booksmartapp.models.Usuario;

public class LoginResponse {

    private String token;

    private String type;

    private String expires_at;

    private Usuario.UsuarioLogin user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public Usuario.UsuarioLogin getUser() {
        return user;
    }

    public void setUser(Usuario.UsuarioLogin user) {
        this.user = user;
    }

    public boolean isRequires_2fa() {
        return requires_2fa;
    }

    public void setRequires_2fa(boolean requires_2fa) {
        this.requires_2fa = requires_2fa;
    }

    private boolean requires_2fa;

}