package com.example.booksmartapp.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booksmartapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrestamoDetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrestamoDetalleFragment extends Fragment {

    public PrestamoDetalleFragment() {
        // Required empty public constructor
    }

    public static PrestamoDetalleFragment newInstance(String param1, String param2) {
        PrestamoDetalleFragment fragment = new PrestamoDetalleFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prestamo_detalle, container, false);
    }
}