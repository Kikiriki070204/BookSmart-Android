package com.example.booksmartapp.responses;

public class UsuarioResponse {
    private int usuario_id;
    private String nombre;
    private String correo;
    private boolean email_sent;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEmail_sent() {
        return email_sent;
    }

    public void setEmail_sent(boolean email_sent) {
        this.email_sent = email_sent;
    }

    public String getEmail_token() {
        return email_token;
    }

    public void setEmail_token(String email_token) {
        this.email_token = email_token;
    }

    private String email_token;

}
