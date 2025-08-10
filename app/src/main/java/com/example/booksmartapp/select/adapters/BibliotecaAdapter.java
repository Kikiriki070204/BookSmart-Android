package com.example.booksmartapp.select.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Biblioteca;
import com.example.booksmartapp.models.Bibliotecas;
import com.example.booksmartapp.select.listeners.BibliotecaListener;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class BibliotecaAdapter extends RecyclerView.Adapter<BibliotecaAdapter.ViewHolder> {

    public List<Biblioteca> bibliotecasList;
    private BibliotecaListener bibliotecaListener;

   public BibliotecaAdapter(List<Biblioteca> bibliotecasList, BibliotecaListener bibliotecaListener) {
         this.bibliotecasList = bibliotecasList;
         this.bibliotecaListener = bibliotecaListener;
   }

    @NonNull
    @Override
    public BibliotecaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly= LayoutInflater.from(parent.getContext());
        View v= ly.inflate(R.layout.item_seleccionar_biblioteca,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BibliotecaAdapter.ViewHolder holder, int position) {
       Biblioteca biblioteca = bibliotecasList.get(position);
       holder.setdata(biblioteca);
       holder.data.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                bibliotecaListener.OnClick(bibliotecasList.get(holder.getAdapterPosition()));
              }
       });

    }

    @Override
    public int getItemCount() {
        return bibliotecasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView data;
        TextView nombre_biblioteca, direccion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.bibliotecaData);
            nombre_biblioteca = itemView.findViewById(R.id.nombre_biblioteca);
            direccion = itemView.findViewById(R.id.direccion);
        }

        public void setdata(Biblioteca biblioteca) {
            nombre_biblioteca.setText(biblioteca.getNombre());
            direccion.setText(biblioteca.getUbicacion());
        }
    }
}
