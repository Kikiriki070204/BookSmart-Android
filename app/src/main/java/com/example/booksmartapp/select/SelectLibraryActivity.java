package com.example.booksmartapp.select;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Biblioteca;
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

            if (bibliotecas != null && bibliotecas.getData() != null && bibliotecas.getData().getBibliotecas() != null) {

                adapter.bibliotecasList.addAll(bibliotecas.getData().getBibliotecas());
            }
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public void OnClick(Biblioteca biblioteca) {

    }
}