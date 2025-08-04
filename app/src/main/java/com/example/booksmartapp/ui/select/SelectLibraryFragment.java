package com.example.booksmartapp.ui.select;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booksmartapp.R;
public class SelectLibraryFragment extends Fragment {


    public SelectLibraryFragment() {
        // Required empty public constructor
    }


    public static SelectLibraryFragment newInstance(String param1, String param2) {
        SelectLibraryFragment fragment = new SelectLibraryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_library, container, false);
    }
}