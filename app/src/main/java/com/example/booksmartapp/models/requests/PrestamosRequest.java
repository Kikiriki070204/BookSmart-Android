package com.example.booksmartapp.models.requests;

public class PrestamosRequest {
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    private int usuarioId;

    public PrestamosRequest(int usuarioId) {
        this.usuarioId = usuarioId;
    }

}
