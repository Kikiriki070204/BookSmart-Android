package com.example.booksmartapp.datos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Biblioteca;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class DatosUsuarioActivity extends AppCompatActivity {
    private TextView nameData, apellidoData, emailData, telefonoData, generoData;
    private View settingsMenu;
    private MaterialButton changePass;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        setUserData();
        setUpSettings();
        setBottomNavigation();
    }
    private void findViews() {
        nameData = findViewById(R.id.nameData);
        apellidoData = findViewById(R.id.apellidoData);
        emailData = findViewById(R.id.emailData);
        telefonoData = findViewById(R.id.numeroData);
        generoData = findViewById(R.id.generoData);

        changePass = findViewById(R.id.changePass);
        settingsMenu = findViewById(R.id.settings);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setUserData() {
        SessionManager sessionManager = SessionManager.getInstance();
        nameData.setText(sessionManager.getUsuario(this).getNombre());
        apellidoData.setText(sessionManager.getUsuario(this).getApellido());
        emailData.setText(sessionManager.getUsuario(this).getCorreo());
        telefonoData.setText(sessionManager.getUsuario(this).getCelular());
        generoData.setText(sessionManager.getUsuario(this).getGenero());
    }

    private void setUpSettings() {
        TextView userDataName = settingsMenu.findViewById(R.id.userDataName);
        ImageButton menuIcon = settingsMenu.findViewById(R.id.menuIcon);

        SessionManager sessionManager = SessionManager.getInstance();
        userDataName.setText(sessionManager.getUsuario(this).getNombre());

        menuIcon.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, menuIcon);
            popup.getMenuInflater().inflate(R.menu.settings_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.userData) {
                    startActivity(new Intent(this, DatosUsuarioActivity.class));
                    return true;
                } else if (id == R.id.change_pass) {
                    startActivity(new Intent(this, CambiarPasswordActivity.class));
                    return true;
                } else if (id == R.id.logout) {
                    // lógica de logout
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    private void setBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, BottomNavigationActivity.class));
                return true;
            } else if (itemId == R.id.nav_search) {
                Intent intent = new Intent(this, BottomNavigationActivity.class);
                intent.putExtra("destino", "search");
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }

}