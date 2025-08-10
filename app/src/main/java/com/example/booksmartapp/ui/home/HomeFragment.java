package com.example.booksmartapp.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.booksmartapp.R;

public class HomeFragment extends Fragment {

    private View rootView;
    private ImageButton menuIcon;
    private RecyclerView bibliotecasRecycler;

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
        findViews();
        return rootView;
    }


    private void findViews()
    {
        menuIcon = getView().findViewById(R.id.menuIcon);
        bibliotecasRecycler = getView().findViewById(R.id.bibliotecasRecycler);
    }
}