package com.example.booksmartapp.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.Prestamo;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PrestamoDetalleFragment extends Fragment {
    private View rootView, dueDaysGreen, dueDaysGray;
    public int totalDias, diasTranscurridos;
    private Prestamo prestamo;
    private TextView titleLibro, autor, datePrestamo, dateEntrega, bookDesc, editorial, remainingDays;
    public int idPrestamo;

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

        if (getArguments() != null) {
            PrestamoDetalleFragmentArgs args = PrestamoDetalleFragmentArgs.fromBundle(getArguments());
            idPrestamo = args.getIdPrestamo();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_prestamo_detalle, container, false);
        findViews();
        setPrestamoData();
        return rootView;
    }

    private void findViews() {
        titleLibro = rootView.findViewById(R.id.tituloLibro);
        autor = rootView.findViewById(R.id.autor);
        datePrestamo = rootView.findViewById(R.id.date_prestamo);
        dateEntrega = rootView.findViewById(R.id.date_entrega);
        bookDesc = rootView.findViewById(R.id.bookDesc);
        editorial = rootView.findViewById(R.id.editorial);
        remainingDays = rootView.findViewById(R.id.remaining_days);

        dueDaysGreen = rootView.findViewById(R.id.dueDaysGreen);
        dueDaysGray = rootView.findViewById(R.id.dueDaysGray);
    }

    private void setPrestamoData() {
        BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(getContext());
        BibliotecasViewModel viewModel = new ViewModelProvider(this, factory).get(BibliotecasViewModel.class);

        if(idPrestamo != 0) {
            viewModel.getPrestamo(idPrestamo).observe(getViewLifecycleOwner(), prestamoResponse -> {
                if (prestamoResponse != null && prestamoResponse.getData() != null) {
                    prestamo = prestamoResponse.getData();

                    titleLibro.setText(prestamo.getLibro_nombre());
                    autor.setText(prestamo.getLibro_autor());
                    datePrestamo.setText(prestamo.getFecha_prestamo());
                    dateEntrega.setText(prestamo.getFecha_devolucion());
                    bookDesc.setText(prestamo.getLibro_descripcion());
                    editorial.setText(prestamo.getLibro_editorial());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate inicio = LocalDate.parse(prestamo.getFecha_prestamo(), formatter);
                        LocalDate fin = LocalDate.parse(prestamo.getFecha_devolucion(), formatter);
                        LocalDate hoy = LocalDate.now();

                        totalDias = (int) ChronoUnit.DAYS.between(inicio, fin) + 1;
                        diasTranscurridos = (int) ChronoUnit.DAYS.between(inicio, hoy) + 1;

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
                        }

                       if(diasTranscurridos >= totalDias)
                       {
                           remainingDays.setText("¡Préstamo pendiente! \nRecuerda entregar el libro cuanto antes y consultar por la multa que se te aplicará");
                           dueDaysGray.setVisibility(View.GONE);
                           dueDaysGreen.setVisibility(View.GONE);
                       }
                       else
                       {
                           remainingDays.setText("¡Te quedan " + (totalDias - diasTranscurridos) + " días antes \ndel día de entrega!");
                       }
                        dueDaysGreen.setLayoutParams(paramsGreen);
                        dueDaysGray.setLayoutParams(paramsGray);

                    } catch (Exception e) {
                    }

                }
                else
                {
                    Toast.makeText(getContext(), "Error al obtener el prestamo", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(getContext(), "Error al obtener el id del préstamo", Toast.LENGTH_SHORT).show();
        }

    }

}