package com.example.booksmartapp.models;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static SessionManager instance;

    public Usuario.UsuarioLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario.UsuarioLogin usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private Usuario.UsuarioLogin usuario;
    private String token;

    private SessionManager() {
        // Constructor privado
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }


    public void saveToken(Context context, String token) {
        this.token = token;
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(Context context) {
        if (token == null) {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            token = prefs.getString("token", null);
        }
        return token;
    }
}