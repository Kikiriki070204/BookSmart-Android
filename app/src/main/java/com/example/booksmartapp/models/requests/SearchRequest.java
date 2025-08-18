package com.example.booksmartapp.models.requests;

public class SearchRequest {
    private String nombre;
    private int bibliotecaId;

    public SearchRequest(String nombre, int bibliotecaId) {
        this.nombre = nombre;
        this.bibliotecaId = bibliotecaId;
    }

}
