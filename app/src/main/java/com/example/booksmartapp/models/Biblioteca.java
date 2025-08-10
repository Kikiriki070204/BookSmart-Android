package com.example.booksmartapp.models;

public class Biblioteca {
    private int id;
    private String nombre;
    private String ubicacion;
    private String estado;

    public Biblioteca(int id, String nombre, String ubicacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Getters y setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
