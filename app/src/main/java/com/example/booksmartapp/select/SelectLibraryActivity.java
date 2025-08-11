package com.example.booksmartapp.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Biblioteca;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.register.VerifyActivity;
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.adapters.BibliotecaAdapter;
import com.example.booksmartapp.select.listeners.BibliotecaListener;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;

import java.util.ArrayList;

public class SelectLibraryActivity extends AppCompatActivity implements BibliotecaListener {

    private RecyclerView bibliotecasRecycler;
    private TextView noBibliotecasText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_library);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        setUserInfo();
        setUpRecyclerView();
    }

    private void findViews()
    {
     bibliotecasRecycler = findViewById(R.id.bibliotecasRecyclerSelect);
     noBibliotecasText = findViewById(R.id.no_bibliotecas);
    }

    private void setUpRecyclerView() {
        BibliotecaAdapter adapter = new BibliotecaAdapter(new ArrayList<>(), this );
        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(this);
        BibliotecasViewModel viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);

        bibliotecasRecycler.setAdapter(adapter);
        bibliotecasRecycler.setLayoutManager(new LinearLayoutManager(this));
        bibliotecasRecycler.setHasFixedSize(true);

        viewModel.getBibliotecas().observe(this, bibliotecas -> {
            adapter.bibliotecasList.clear();

            if (bibliotecas != null && bibliotecas.getData() != null && bibliotecas.getData().getBibliotecas() != null && !bibliotecas.getData().getBibliotecas().isEmpty()) {
                SessionManager sessionManager =  SessionManager.getInstance();
                sessionManager.setBibliotecas(bibliotecas.getData().getBibliotecas());
                adapter.bibliotecasList.addAll(bibliotecas.getData().getBibliotecas());
                noBibliotecasText.setVisibility(View.GONE);
                bibliotecasRecycler.setVisibility(View.VISIBLE);
            } else {
                noBibliotecasText.setVisibility(View.VISIBLE);
                bibliotecasRecycler.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public void OnClick(Biblioteca biblioteca) {
        SessionManager sessionManager = SessionManager.getInstance();
        sessionManager.setBibliotecaSeleccionadaId(biblioteca.getId());
        Intent intent = new Intent(this, BottomNavigationActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUserInfo() {
        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(this);
        BibliotecasViewModel viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);
        viewModel.getUsuarioInfo().observe(this, usuarioResponse -> {
            if (usuarioResponse != null && usuarioResponse.getData() != null) {
                SessionManager sessionManager = SessionManager.getInstance();
                sessionManager.saveUsuario(this, usuarioResponse.getData());
            } else {
                Toast.makeText(this, "Error al obtener la información del usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}