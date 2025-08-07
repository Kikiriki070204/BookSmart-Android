package com.example.booksmartapp.models;

public class SessionManager {
    private static Usuario usuario;

    public static int getUser_id() {
        return user_id;
    }

    private static int user_id;
    public static void setTemporalId(int id)
    {
        user_id = id;
    }

    public static void setUsuarioId(int id) {
     usuario.setId(id);
    }

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void clearUsuario() {
        usuario = null;
    }

}
