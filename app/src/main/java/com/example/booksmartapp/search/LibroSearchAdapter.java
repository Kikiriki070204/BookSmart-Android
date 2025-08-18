package com.example.booksmartapp.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.LibroBiblioteca;
import com.example.booksmartapp.search.listeners.LibroListener;
import com.example.booksmartapp.select.adapters.PrestamosAdapter;

import java.util.List;

public class LibroSearchAdapter extends RecyclerView.Adapter<LibroSearchAdapter.ViewHolder> {
    public List<LibroBiblioteca> librosList;
    public LibroListener libroListener;

    public LibroSearchAdapter(List<LibroBiblioteca> librosList, LibroListener libroListener) {
        this.librosList = librosList;
        this.libroListener = libroListener;
    }

    @NonNull
    @Override
    public LibroSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly= LayoutInflater.from(parent.getContext());
        View v= ly.inflate(R.layout.item_libro_busqueda,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroSearchAdapter.ViewHolder holder, int position) {
        LibroBiblioteca libroBiblioteca = librosList.get(position);
        holder.setdata(libroBiblioteca);
        holder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libroListener.OnClick(librosList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return librosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View data;
        public TextView titulo, autor, isbn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.cardLibro);
            titulo = itemView.findViewById(R.id.tituloLibro);
            autor = itemView.findViewById(R.id.autorLibro);
            isbn = itemView.findViewById(R.id.isbnLibro);
        }

        public void setdata(LibroBiblioteca libroBiblioteca) {
            titulo.setText(libroBiblioteca.getNombre());
            autor.setText(libroBiblioteca.getAutor());
            isbn.setText(libroBiblioteca.getIsbn());
        }
    }
}
