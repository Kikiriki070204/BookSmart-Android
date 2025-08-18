package com.example.booksmartapp.select.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.select.listeners.PrestamoListener;
import com.google.android.material.card.MaterialCardView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PrestamosAdapter extends RecyclerView.Adapter<PrestamosAdapter.ViewHolder> {

    public List<Prestamo> prestamosList;
    public PrestamoListener prestamoListener;

    public PrestamosAdapter(List<Prestamo> prestamosList, PrestamoListener prestamoListener) {
        this.prestamosList = prestamosList;
        this.prestamoListener = prestamoListener;
    }

    @NonNull
    @Override
    public PrestamosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly= LayoutInflater.from(parent.getContext());
        View v= ly.inflate(R.layout.item_prestamo,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestamosAdapter.ViewHolder holder, int position) {
        Prestamo prestamo = prestamosList.get(position);
        holder.setdata(prestamo);
        holder.data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                prestamoListener.OnClick(prestamosList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {

        return prestamosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public MaterialCardView data;
        public TextView tituloLibro, progreso;
        public View dueDaysGreen, dueDaysGray;
        public int totalDias, diasTranscurridos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.cardPrestamo);
            tituloLibro = itemView.findViewById(R.id.tituloLibro);
            progreso = itemView.findViewById(R.id.progreso);
            dueDaysGray = itemView.findViewById(R.id.dueDaysGray);
            dueDaysGreen = itemView.findViewById(R.id.dueDaysGreen);
        }

        public void setdata(Prestamo prestamo) {
            tituloLibro.setText(prestamo.getLibro_nombre());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate inicio = LocalDate.parse(prestamo.getFecha_prestamo(), formatter);
                LocalDate fin = LocalDate.parse(prestamo.getFecha_devolucion(), formatter);
                LocalDate hoy = LocalDate.now();

                totalDias = (int) ChronoUnit.DAYS.between(inicio, fin) + 1;
                diasTranscurridos = (int) ChronoUnit.DAYS.between(inicio, hoy) + 1;

                tituloLibro.setText(prestamo.getLibro_nombre());
                progreso.setText(diasTranscurridos + "/" + totalDias);
            } catch (Exception e) {
                progreso.setText("Fechas inválidas");
            }

            LinearLayout.LayoutParams paramsGreen = (LinearLayout.LayoutParams) dueDaysGreen.getLayoutParams();
            LinearLayout.LayoutParams paramsGray = (LinearLayout.LayoutParams) dueDaysGray.getLayoutParams();

            if (diasTranscurridos == 1) {
                paramsGreen.weight = 1f;
                paramsGray.weight = 3f;
                dueDaysGray.setVisibility(View.VISIBLE);
            } else if (diasTranscurridos > 1 && diasTranscurridos < totalDias) {
                paramsGreen.weight = 1f;
                paramsGray.weight = 1f;
                dueDaysGray.setVisibility(View.VISIBLE);
            } else if (diasTranscurridos == totalDias) {
                paramsGreen.weight = 3f;
                paramsGray.weight = 1f;
                dueDaysGray.setVisibility(View.GONE);
            }else if(diasTranscurridos >= totalDias)
            {
                int diasRetraso = diasTranscurridos - totalDias;
                progreso.setText(diasRetraso + " días de retraso");
                progreso.setTextColor(itemView.getResources().getColor(R.color.unavailable));
                paramsGray.weight = 4f;
                dueDaysGreen.setVisibility(View.GONE);
            }

            dueDaysGreen.setLayoutParams(paramsGreen);
            dueDaysGray.setLayoutParams(paramsGray);
        }
    }
}
