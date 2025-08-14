package com.example.booksmartapp.models;


public class Prestamo {
    private int id;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private String fecha_entregaReal;
    private String estado;

    private String libro_nombre;
    private String libro_autor;
    private String libro_isbn;
    private String libro_editorial;

    public String getLibro_editorial() {
        return libro_editorial;
    }

    public void setLibro_editorial(String libro_editorial) {
        this.libro_editorial = libro_editorial;
    }

    public String getLibro_descricpcion() {
        return libro_descricpcion;
    }

    public void setLibro_descricpcion(String libro_descricpcion) {
        this.libro_descricpcion = libro_descricpcion;
    }

    private String libro_descricpcion;

    private String biblioteca_nombre;
    private String seccion_etiqueta;

    private String usuario_nombre;
    private String usuario_email;
    private String usuario_codigoTarjeta;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFecha_prestamo() { return fecha_prestamo; }
    public void setFecha_prestamo(String fecha_prestamo) { this.fecha_prestamo = fecha_prestamo; }

    public String getFecha_devolucion() { return fecha_devolucion; }
    public void setFecha_devolucion(String fecha_devolucion) { this.fecha_devolucion = fecha_devolucion; }

    public String getFechaEntregaReal() { return fecha_entregaReal; }
    public void setFechaEntregaReal(String fechaEntregaReal) { this.fecha_entregaReal = fechaEntregaReal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getLibro_nombre() { return libro_nombre; }
    public void setLibro_nombre(String libro_nombre) { this.libro_nombre = libro_nombre; }

    public String getLibro_autor() { return libro_autor; }
    public void setLibro_autor(String libro_autor) { this.libro_autor = libro_autor; }

    public String getLibro_isbn() { return libro_isbn; }
    public void setLibro_isbn(String libro_isbn) { this.libro_isbn = libro_isbn; }

    public String getBibliotecaNombre() { return biblioteca_nombre; }
    public void setBibliotecaNombre(String bibliotecaNombre) { this.biblioteca_nombre = bibliotecaNombre; }

    public String getSeccion_etiqueta() { return seccion_etiqueta; }
    public void setSeccion_etiqueta(String seccion_etiqueta) { this.seccion_etiqueta = seccion_etiqueta; }

    public String getUsuarioNombre() { return usuario_nombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuario_nombre = usuarioNombre; }

    public String getUsuarioEmail() { return usuario_email; }
    public void setUsuarioEmail(String usuarioEmail) { this.usuario_email = usuarioEmail; }

    public String getUsuarioCodigoTarjeta() { return usuario_codigoTarjeta; }
    public void setUsuarioCodigoTarjeta(String usuarioCodigoTarjeta) { this.usuario_codigoTarjeta = usuarioCodigoTarjeta; }
}