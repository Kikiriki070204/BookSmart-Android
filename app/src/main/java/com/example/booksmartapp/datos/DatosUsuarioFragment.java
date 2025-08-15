package com.example.booksmartapp.datos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.MainActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class DatosUsuarioFragment extends Fragment {
    private TextView nameData, apellidoData, emailData, telefonoData, generoData;
    private View rootView;
    private MaterialButton changePass;
    private SessionManager sessionManager;

    public DatosUsuarioFragment() {
    }

    public static DatosUsuarioFragment newInstance(String param1, String param2) {
        DatosUsuarioFragment fragment = new DatosUsuarioFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_datos_usuario, container, false);
        sessionManager = SessionManager.getInstance();
        findViews();
        setUserData();
        setUpButton();
        return rootView;
    }

    private void findViews() {
        nameData = rootView.findViewById(R.id.nameData);
        apellidoData = rootView.findViewById(R.id.apellidoData);
        emailData = rootView.findViewById(R.id.emailData);
        telefonoData = rootView.findViewById(R.id.numeroData);
        generoData = rootView.findViewById(R.id.generoData);

        changePass = rootView.findViewById(R.id.changePass);
    }

    private void setUserData() {
        nameData.setText(sessionManager.getUsuario(getContext()).getNombre());
        apellidoData.setText(sessionManager.getUsuario(getContext()).getApellido());
        emailData.setText(sessionManager.getUsuario(getContext()).getCorreo());
        telefonoData.setText(sessionManager.getUsuario(getContext()).getCelular());
        generoData.setText(sessionManager.getUsuario(getContext()).getGenero());
    }

    private void setUpButton()
    {
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.actionDatosToCambiarPassword);
            }
        });

    }
}