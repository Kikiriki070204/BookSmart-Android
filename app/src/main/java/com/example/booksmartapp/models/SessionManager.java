package com.example.booksmartapp.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class SessionManager {
    private static SessionManager instance;

    private List<Biblioteca> bibliotecas;

    private int bibliotecaSeleccionadaId;

    private Usuario usuario;

    public Usuario.UsuarioLogin getUser() {
        return user;
    }

    public void setUser(Usuario.UsuarioLogin user) {
        this.user = user;
    }

    private Usuario.UsuarioLogin user;
    private String token;

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    private String contrasena;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(List<Biblioteca> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public int getBibliotecaSeleccionadaId() {
        return bibliotecaSeleccionadaId;
    }

    public void setBibliotecaSeleccionadaId(int bibliotecaSeleccionadaId) {
        this.bibliotecaSeleccionadaId = bibliotecaSeleccionadaId;
    }

    public SessionManager() {
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


    public void saveUsuario(Context context, Usuario usuario) {
        this.usuario = usuario;
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String usuarioJson = new Gson().toJson(usuario);
        editor.putString("usuario", usuarioJson);
        editor.apply();
    }

    public Usuario getUsuario(Context context) {
        if (usuario == null) {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String usuarioJson = prefs.getString("usuario", null);
            if (usuarioJson != null) {
                usuario = new Gson().fromJson(usuarioJson, Usuario.class);
            }
        }
        return usuario;
    }

    public void saveContrasena(Context context, String contrasena) {
        this.contrasena = contrasena;
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("contrasena", contrasena);
        editor.apply();
    }

    public String getContrasena(Context context) {
        if (contrasena == null) {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            contrasena = prefs.getString("contrasena", null);
        }
        return contrasena;
    }

    public void saveBibliotecaSeleccionadaId(Context context, int id) {
        this.bibliotecaSeleccionadaId = id;
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("bibliotecaSeleccionadaId", id);
        editor.apply();
    }

    public int getBibliotecaSeleccionadaId(Context context) {
        if (bibliotecaSeleccionadaId == 0) {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            bibliotecaSeleccionadaId = prefs.getInt("bibliotecaSeleccionadaId", 0);
        }
        return bibliotecaSeleccionadaId;
    }

    public void logout(Context context) {
        usuario = null;
        user = null;
        token = null;
        contrasena = null;
        bibliotecas = null;
        bibliotecaSeleccionadaId = 0;

        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }


}