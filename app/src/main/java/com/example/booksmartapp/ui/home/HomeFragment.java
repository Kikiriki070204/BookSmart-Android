package com.example.booksmartapp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.Usuario;
import com.example.booksmartapp.models.requests.PrestamosRequest;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.adapters.PrestamosAdapter;
import com.example.booksmartapp.select.listeners.PrestamoListener;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PrestamoListener {

    private View rootView;
    private RecyclerView prestamosRecycler;
    private SessionManager sessionManager;
    private TextView noPrestamosText;
    private Usuario usuario;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = SessionManager.getInstance();
        usuario =  sessionManager.getUsuario(requireContext());
        findViews();
        setUpPrestamosRecycler();
        return rootView;
    }

    private void findViews() {
        prestamosRecycler = rootView.findViewById(R.id.prestamosRecyclerView);
        noPrestamosText = rootView.findViewById(R.id.no_prestamos);
    }

    private void setUpPrestamosRecycler() {
        PrestamosAdapter adapter = new PrestamosAdapter(new ArrayList<>(), this);
        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(getContext());
        BibliotecasViewModel viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);

        prestamosRecycler.setAdapter(adapter);
        prestamosRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        prestamosRecycler.setHasFixedSize(true);

        int id_biblioteca  = sessionManager.getBibliotecaSeleccionadaId(requireContext());
        int id_usuario = usuario.getId();

        viewModel.getPrestamos(id_biblioteca, id_usuario ).observe(getViewLifecycleOwner(), prestamosResponse -> {
            adapter.prestamosList.clear();
            if (prestamosResponse != null && prestamosResponse.getData() != null) {
                List<Prestamo> prestamos = prestamosResponse.getData();
                if (prestamos != null && !prestamos.isEmpty()) {
                    adapter.prestamosList.addAll(prestamos);
                    noPrestamosText.setVisibility(View.GONE);
                    prestamosRecycler.setVisibility(View.VISIBLE);
                } else {
                    noPrestamosText.setVisibility(View.VISIBLE);
                    prestamosRecycler.setVisibility(View.GONE);
                }
            } else {
                noPrestamosText.setVisibility(View.VISIBLE);
                prestamosRecycler.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void OnClick(Prestamo prestamo) {
    int idPrestamo = prestamo.getId();

    NavDirections action = HomeFragmentDirections.actionHomeToDetalleFragment(idPrestamo);
    NavHostFragment.findNavController(this).navigate(action);
    }
}