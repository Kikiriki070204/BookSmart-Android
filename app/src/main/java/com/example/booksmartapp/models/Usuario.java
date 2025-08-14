package com.example.booksmartapp.models;

public class Usuario {
    private int user_id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String celular;
    private String genero;
    private DatosAdicionales datos_adicionales;
    private String  role;


    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    public DatosAdicionales getDatos_adicionales() {
        return datos_adicionales;
    }

    public void setDatos_adicionales(DatosAdicionales datos_adicionales) {
        this.datos_adicionales = datos_adicionales;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int isActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    private int activo;




    public static class UsuarioLogin {
        private int id;
        private String nombre;
        private String rol;
        private String correo;
        private int bibliotecaId;

        public UsuarioLogin(int id, String nombre, String rol, String correo, int bibliotecaId) {
            this.id = id;
            this.nombre = nombre;
            this.rol = rol;
            this.correo = correo;
            this.bibliotecaId = bibliotecaId;
        }

        // Getters y setters
        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getRol() { return rol; }
        public String getCorreo() { return correo; }
        public int getBibliotecaId() { return bibliotecaId; }
    }
}

