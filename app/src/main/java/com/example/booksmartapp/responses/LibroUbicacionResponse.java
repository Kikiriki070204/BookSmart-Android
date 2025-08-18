package com.example.booksmartapp.responses;

public class LibroUbicacionResponse {
    public int libro_id;
    public String libro_nombre;
    public String seccion_etiqueta;
    public String estante_etiqueta;
    public int estante_filas;
    public int estante_columnas;
    public int libro_fila;

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public String getLibro_nombre() {
        return libro_nombre;
    }

    public void setLibro_nombre(String libro_nombre) {
        this.libro_nombre = libro_nombre;
    }

    public String getSeccion_etiqueta() {
        return seccion_etiqueta;
    }

    public void setSeccion_etiqueta(String seccion_etiqueta) {
        this.seccion_etiqueta = seccion_etiqueta;
    }

    public String getEstante_etiqueta() {
        return estante_etiqueta;
    }

    public void setEstante_etiqueta(String estante_etiqueta) {
        this.estante_etiqueta = estante_etiqueta;
    }

    public int getEstante_filas() {
        return estante_filas;
    }

    public void setEstante_filas(int estante_filas) {
        this.estante_filas = estante_filas;
    }

    public int getEstante_columnas() {
        return estante_columnas;
    }

    public void setEstante_columnas(int estante_columnas) {
        this.estante_columnas = estante_columnas;
    }

    public int getLibro_fila() {
        return libro_fila;
    }

    public void setLibro_fila(int libro_fila) {
        this.libro_fila = libro_fila;
    }

    public int getLibro_columna() {
        return libro_columna;
    }

    public void setLibro_columna(int libro_columna) {
        this.libro_columna = libro_columna;
    }

    public int libro_columna;
    public LibroUbicacionResponse(int libro_id, String libro_nombre, String seccion_etiqueta, String estante_etiqueta, int estante_filas, int estante_columnas, int libro_fila, int libro_columna) {
        this.libro_id = libro_id;
        this.libro_nombre = libro_nombre;
        this.seccion_etiqueta = seccion_etiqueta;
        this.estante_etiqueta = estante_etiqueta;
        this.estante_filas = estante_filas;
        this.estante_columnas = estante_columnas;
        this.libro_fila = libro_fila;
        this.libro_columna = libro_columna;
    }

}
