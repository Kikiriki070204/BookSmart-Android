package com.example.booksmartapp.models;

public class Prestamo {
    private int id;
    private int libro_biblioteca_id;
    private int usuario_biblioteca_id;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private String estado;

    private String libro_nombre;
    private String libro_autor;
    private String libro_isbn;
    private String biblioteca_nombre;
    private String seccion_etiqueta;

    private String usuario_nombre;
    private String usuario_email;
    private String usuario_codigo_tarjeta;

    public String getLibro_nombre() {
        return libro_nombre;
    }

    public void setLibro_nombre(String libro_nombre) {
        this.libro_nombre = libro_nombre;
    }

    public String getLibro_autor() {
        return libro_autor;
    }

    public void setLibro_autor(String libro_autor) {
        this.libro_autor = libro_autor;
    }

    public String getLibro_isbn() {
        return libro_isbn;
    }

    public void setLibro_isbn(String libro_isbn) {
        this.libro_isbn = libro_isbn;
    }

    public String getBiblioteca_nombre() {
        return biblioteca_nombre;
    }

    public void setBiblioteca_nombre(String biblioteca_nombre) {
        this.biblioteca_nombre = biblioteca_nombre;
    }

    public String getSeccion_etiqueta() {
        return seccion_etiqueta;
    }

    public void setSeccion_etiqueta(String seccion_etiqueta) {
        this.seccion_etiqueta = seccion_etiqueta;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_codigo_tarjeta() {
        return usuario_codigo_tarjeta;
    }

    public void setUsuario_codigo_tarjeta(String usuario_codigo_tarjeta) {
        this.usuario_codigo_tarjeta = usuario_codigo_tarjeta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLibro_biblioteca_id() {
        return libro_biblioteca_id;
    }

    public void setLibro_biblioteca_id(int libro_biblioteca_id) {
        this.libro_biblioteca_id = libro_biblioteca_id;
    }

    public int getUsuario_biblioteca_id() {
        return usuario_biblioteca_id;
    }

    public void setUsuario_biblioteca_id(int usuario_biblioteca_id) {
        this.usuario_biblioteca_id = usuario_biblioteca_id;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_entrega_real() {
        return fecha_entrega_real;
    }

    public void setFecha_entrega_real(String fecha_entrega_real) {
        this.fecha_entrega_real = fecha_entrega_real;
    }

    private String fecha_entrega_real;

    public Prestamo(int id, int libro_biblioteca_id, int usuario_biblioteca_id, String fecha_prestamo, String fecha_devolucion, String estado, String fecha_entrega_real) {
        this.id = id;
        this.libro_biblioteca_id = libro_biblioteca_id;
        this.usuario_biblioteca_id = usuario_biblioteca_id;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.fecha_entrega_real = fecha_entrega_real;
    }

}
