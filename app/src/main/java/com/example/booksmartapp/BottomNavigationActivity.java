package com.example.booksmartapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.booksmartapp.models.Biblioteca;
import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.adapters.BibliotecaAdapter;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity {

    private AutoCompleteTextView dropdownBibliotecas;
    private BottomNavigationView bottomNavigation;
    private NavController navController;

    private int selectedLibraryId = -1;

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
        findViews();
        loadBibliotecas();

    }

    private void findViews(){
        dropdownBibliotecas = findViewById(R.id.dropdownBibliotecas);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
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
                    sessionManager.setBibliotecaSeleccionadaId(selectedLibraryId);
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


}