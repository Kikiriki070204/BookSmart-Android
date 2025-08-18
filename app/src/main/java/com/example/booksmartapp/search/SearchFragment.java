package com.example.booksmartapp.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.LibroBiblioteca;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.requests.SearchRequest;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.search.listeners.LibroListener;
import com.example.booksmartapp.select.adapters.PrestamosAdapter;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements LibroListener {

    private View rootView;
    private SearchView searchView;
    private MaterialCardView resultCard;
    private RecyclerView resultadosRecycler;
    private MaterialButton btnBookState;
    private LibroSearchAdapter adapter;
    private BibliotecasViewModel viewModel;
    private LinearLayout bookData;
    private TextView bookTitle, bookShelf;

    private int bibliotecaId, libroBibliotecaId, cantidadColumnas, cantidadFilas;
    private SessionManager sessionManager;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_search, container, false);
        sessionManager = SessionManager.getInstance();
        findViews();
        setSearchViewText();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LibroSearchAdapter(new ArrayList<>(), this);
        resultadosRecycler.setAdapter(adapter);
        resultadosRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        resultadosRecycler.setHasFixedSize(true);

        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(getContext());
        viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);
        bibliotecaId = sessionManager.getBibliotecaSeleccionadaId(requireContext());
    }

    private void findViews() {
        searchView = rootView.findViewById(R.id.searchView);
        resultCard = rootView.findViewById(R.id.resultsCard);
        resultadosRecycler = rootView.findViewById(R.id.resultadosRecycler);
        btnBookState = rootView.findViewById(R.id.bookState);
        bookData = rootView.findViewById(R.id.bookData);
        bookTitle = rootView.findViewById(R.id.bookTitle);
        bookShelf = rootView.findViewById(R.id.bookShelf);
    }

    public void setBusquedaRecycler(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.trim().isEmpty()) {
            resultadosRecycler.setVisibility(View.GONE);
            resultCard.setVisibility(View.GONE);
            return;
        }

        SearchRequest request = new SearchRequest(
                textoBusqueda.trim(),
                bibliotecaId
        );

        viewModel.getLibrosByNameSearch(request).observe(getViewLifecycleOwner(), librosResponse -> {
           adapter.librosList.clear();
            if (librosResponse != null && librosResponse.getData() != null) {
                List<LibroBiblioteca> libros = librosResponse.getData();
                if (libros != null && !libros.isEmpty()) {
                    adapter.librosList.addAll(libros);
                    resultCard.setVisibility(View.VISIBLE);
                    resultadosRecycler.setVisibility(View.VISIBLE);
                } else {
                    resultCard.setVisibility(View.GONE);
                    resultadosRecycler.setVisibility(View.GONE);
                }
            } else {
                resultCard.setVisibility(View.GONE);
                resultadosRecycler.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void setSearchViewText()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setBusquedaRecycler(newText);
                return true;
            }
        });
    }

    @Override
    public void OnClick(LibroBiblioteca libroBiblioteca) {
    libroBibliotecaId = libroBiblioteca.getLibro_biblioteca_id();
    if(libroBiblioteca.getEstado().equals("Disponible"))
    {
        btnBookState.setBackgroundColor(getResources().getColor(R.color.PopUps));
    }
    else
    {
        btnBookState.setBackgroundColor(getResources().getColor(R.color.unavailable));
    }
    btnBookState.setText(libroBiblioteca.getEstado());

    }

    private void setLibroUbicacion()
    {
        viewModel.getLibroUbicacion(libroBibliotecaId).observe(getViewLifecycleOwner(), libroUbicacionResponse -> {
            if (libroUbicacionResponse != null && libroUbicacionResponse.getData() != null) {
                bookData.setVisibility(View.VISIBLE);
                bookTitle.setText(libroUbicacionResponse.getData().getLibro_nombre());
                bookShelf.setText(libroUbicacionResponse.getData().getEstante_etiqueta());

                //para dibujado
                cantidadColumnas = libroUbicacionResponse.getData().getEstante_columnas();
                cantidadFilas = libroUbicacionResponse.getData().getEstante_filas();
            }
        });
    }
}