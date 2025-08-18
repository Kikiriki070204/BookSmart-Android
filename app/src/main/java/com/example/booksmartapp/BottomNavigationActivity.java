package com.example.booksmartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.example.booksmartapp.models.Biblioteca;
import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.adapters.BibliotecaAdapter;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;
import com.example.booksmartapp.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity {

    private AutoCompleteTextView dropdownBibliotecas;
    private BottomNavigationView bottomNavigation;
    private NavController navController;

    private int selectedLibraryId = -1;

    private View settingsMenu;
    private FrameLayout bibliotecas;

    private List<Biblioteca> listaBibliotecas;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bottom_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sessionManager = SessionManager.getInstance();
        sessionManager.getUsuario(this);
        sessionManager.getToken(this);
        findViews();
        setUpSettings();
        loadBibliotecas();
        Intent intent = getIntent();
        String destino = intent.getStringExtra("destino");
        manageNavigationItems();
        if ("search".equals(destino)) {
            navController.navigate(R.id.searchFragment);
        }

    }

    private void findViews(){
        dropdownBibliotecas = findViewById(R.id.dropdownBibliotecas);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        settingsMenu = findViewById(R.id.settings);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bibliotecas = findViewById(R.id.Bibliotecas);
    }

    private void setDropdown(){
        List<String> nombresBibliotecas = new ArrayList<>();
        for (Biblioteca b : listaBibliotecas) {
            nombresBibliotecas.add(b.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                nombresBibliotecas
        );

        dropdownBibliotecas.setAdapter(adapter);
    }

    private void setBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                navController.navigate(R.id.homeFragment);
                return true;
            } else if (itemId == R.id.nav_search) {
                navController.navigate(R.id.searchFragment);
                return true;
            } else {
                return false;
            }
        });


        int idSeleccionado = sessionManager.getBibliotecaSeleccionadaId();
        for (int i = 0; i < listaBibliotecas.size(); i++) {
            if (listaBibliotecas.get(i).getId() == idSeleccionado) {
                dropdownBibliotecas.setText(listaBibliotecas.get(i).getNombre(), false);
                break;
            }
        }

        dropdownBibliotecas.setOnItemClickListener((parent, view, position, id) -> {
            String nombreSeleccionado = parent.getItemAtPosition(position).toString();

            for (Biblioteca b : listaBibliotecas) {
                if (b.getNombre().equals(nombreSeleccionado)) {
                    selectedLibraryId = b.getId();
                    sessionManager.saveBibliotecaSeleccionadaId(BottomNavigationActivity.this,selectedLibraryId);

                    NavDestination currentDestination = navController.getCurrentDestination();
                    int currentFragmentId = currentDestination != null ? currentDestination.getId() : -1;
                    if (currentFragmentId == R.id.homeFragment) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, new HomeFragment());
                        transaction.commit();
                    } else {
                        navController.navigate(currentFragmentId);
                    }
                    break;
                }
            }
        });

    }

    private void loadBibliotecas() {

        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(this);
        BibliotecasViewModel viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);
        viewModel.getBibliotecas().observe(this, bibliotecas -> {

            if (bibliotecas != null && bibliotecas.getData() != null && bibliotecas.getData().getBibliotecas() != null) {
                listaBibliotecas = bibliotecas.getData().getBibliotecas();
                sessionManager.setBibliotecas(listaBibliotecas);
                setDropdown();
                setBottomNavigation();
            } else {
                listaBibliotecas = new ArrayList<>();
            }
        });
    }

    private void manageNavigationItems()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.detalleFragment) {
                bibliotecas.setVisibility(View.GONE);
                settingsMenu.setVisibility(View.GONE);
                bottomNavigation.setVisibility(View.GONE);
            } else if(destination.getId() == R.id.datosFragment) {
                bibliotecas.setVisibility(View.GONE);
            } else if (destination.getId() == R.id.searchFragment) {
                bibliotecas.setVisibility(View.VISIBLE);
                settingsMenu.setVisibility(View.GONE);
            } else {
                    bibliotecas.setVisibility(View.VISIBLE);
                    settingsMenu.setVisibility(View.VISIBLE);
                    bottomNavigation.setVisibility(View.VISIBLE);
                }
        });
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
                    navController.navigate(R.id.datosFragment);
                    return true;
                } else if (id == R.id.change_pass) {
                    navController.navigate(R.id.cambiarPasswordFragment);
                    return true;
                } else if (id == R.id.logout) {
                    logout();
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    private void logout()
    {
        sessionManager.logout(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}