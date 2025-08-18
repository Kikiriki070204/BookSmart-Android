package com.example.booksmartapp.models;

public class LibroBiblioteca {
    private int libro_id;
    private int libro_biblioteca_id;
    private String nombre;
    private String autor;
    private String isbn;
    private String editorial;
    private String descripcion;
    private int biblioteca_id;
    private String biblioteca_nombre;
    private int seccion_id;
    private String seccion_etiqueta;
    private int copias;

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public int getLibro_biblioteca_id() {
        return libro_biblioteca_id;
    }

    public void setLibro_biblioteca_id(int libro_biblioteca_id) {
        this.libro_biblioteca_id = libro_biblioteca_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBiblioteca_id() {
        return biblioteca_id;
    }

    public void setBiblioteca_id(int biblioteca_id) {
        this.biblioteca_id = biblioteca_id;
    }

    public String getBiblioteca_nombre() {
        return biblioteca_nombre;
    }

    public void setBiblioteca_nombre(String biblioteca_nombre) {
        this.biblioteca_nombre = biblioteca_nombre;
    }

    public int getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(int seccion_id) {
        this.seccion_id = seccion_id;
    }

    public String getSeccion_etiqueta() {
        return seccion_etiqueta;
    }

    public void setSeccion_etiqueta(String seccion_etiqueta) {
        this.seccion_etiqueta = seccion_etiqueta;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private String estado;

    public LibroBiblioteca(int libro_id, int libro_biblioteca_id, String nombre, String autor, String isbn,String editorial, String descripcion, int biblioteca_id, String biblioteca_nombre, int seccion_id, String seccion_etiqueta, int copias, String estado) {
        this.libro_id = libro_id;
        this.libro_biblioteca_id = libro_biblioteca_id;
        this.nombre = nombre;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.descripcion = descripcion;
        this.biblioteca_id = biblioteca_id;
        this.biblioteca_nombre = biblioteca_nombre;
        this.seccion_id = seccion_id;
        this.seccion_etiqueta = seccion_etiqueta;
        this.copias = copias;
        this.estado = estado;
    }


}
