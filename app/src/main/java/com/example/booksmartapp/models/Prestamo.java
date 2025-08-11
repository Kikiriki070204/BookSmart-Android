package com.example.booksmartapp.models;


public class Prestamo {
    private int id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private String fechaEntregaReal;
    private String estado;

    private String libroNombre;
    private String libroAutor;
    private String libroIsbn;

    private String bibliotecaNombre;
    private String seccionEtiqueta;

    private String usuarioNombre;
    private String usuarioEmail;
    private String usuarioCodigoTarjeta;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(String fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public String getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(String fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getFechaEntregaReal() { return fechaEntregaReal; }
    public void setFechaEntregaReal(String fechaEntregaReal) { this.fechaEntregaReal = fechaEntregaReal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getLibroNombre() { return libroNombre; }
    public void setLibroNombre(String libroNombre) { this.libroNombre = libroNombre; }

    public String getLibroAutor() { return libroAutor; }
    public void setLibroAutor(String libroAutor) { this.libroAutor = libroAutor; }

    public String getLibroIsbn() { return libroIsbn; }
    public void setLibroIsbn(String libroIsbn) { this.libroIsbn = libroIsbn; }

    public String getBibliotecaNombre() { return bibliotecaNombre; }
    public void setBibliotecaNombre(String bibliotecaNombre) { this.bibliotecaNombre = bibliotecaNombre; }

    public String getSeccionEtiqueta() { return seccionEtiqueta; }
    public void setSeccionEtiqueta(String seccionEtiqueta) { this.seccionEtiqueta = seccionEtiqueta; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public String getUsuarioEmail() { return usuarioEmail; }
    public void setUsuarioEmail(String usuarioEmail) { this.usuarioEmail = usuarioEmail; }

    public String getUsuarioCodigoTarjeta() { return usuarioCodigoTarjeta; }
    public void setUsuarioCodigoTarjeta(String usuarioCodigoTarjeta) { this.usuarioCodigoTarjeta = usuarioCodigoTarjeta; }
}