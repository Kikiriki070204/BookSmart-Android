package com.example.booksmartapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.Usuario;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SessionManager sessionManager = SessionManager.getInstance();
        String token = sessionManager.getToken(SplashScreen.this);
        Usuario usuario = sessionManager.getUsuario(SplashScreen.this);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (token != null && !token.isEmpty() && usuario != null) {
                    Usuario user;
                    user = sessionManager.getUsuario(SplashScreen.this);
                    sessionManager.setUsuario(user);
                    Intent intent = new Intent(SplashScreen.this, BottomNavigationActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 2000);

        /*
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
        SessionManager sessionManager = SessionManager.getInstance();
        String token = sessionManager.getToken(SplashScreen.this);
        Usuario usuario = sessionManager.getUsuario(SplashScreen.this);

        if (token != null && !token.isEmpty() && usuario != null) {
            // Usuario logeado, redirigir a BottomNavActivity
            Intent intent = new Intent(SplashScreen.this, BottomNavActivity.class);
            startActivity(intent);
        } else {
            // No logeado, redirigir a MainActivity (login)
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }

        finish();
    }, 2000);

         */
    }
}