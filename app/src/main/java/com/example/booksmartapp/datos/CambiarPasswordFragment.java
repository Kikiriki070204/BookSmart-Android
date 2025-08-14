package com.example.booksmartapp.datos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.requests.ChangePassRequest;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class CambiarPasswordFragment extends Fragment {
    private TextInputEditText editPasswordActual, editPasswordNueva, editPasswordConfirmar;
    private TextView errorPasswordActual, errorPasswordNueva, errorPasswordConfirmar;

    private boolean isPasswordActualValid = false;
    private boolean isPasswordNuevaValid = false;
    private boolean isPasswordConfirmarValid = false;
    private MaterialButton btnChangePassword;
    private Pattern passwordPattern = Pattern.compile("^((?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*#?&])).{8,}$");
    private View rootView;
    private SessionManager sessionManager;

    public CambiarPasswordFragment() {
        // Required empty public constructor
    }

    public static CambiarPasswordFragment newInstance(String param1, String param2) {
        CambiarPasswordFragment fragment = new CambiarPasswordFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cambiar_password, container, false);
        sessionManager = SessionManager.getInstance();
        findViews();
        setValidations();
        setBtnChangePassword();
        return rootView;
    }

    private void findViews() {
        editPasswordActual = rootView.findViewById(R.id.editPasswordActual);
        editPasswordNueva = rootView.findViewById(R.id.editPasswordNueva);
        editPasswordConfirmar = rootView.findViewById(R.id.editConfPasswordNueva);
        errorPasswordActual = rootView.findViewById(R.id.errorPasswordActual);
        errorPasswordNueva = rootView.findViewById(R.id.errorPasswordNueva);
        errorPasswordConfirmar = rootView.findViewById(R.id.errorConfPasswordNueva);
        btnChangePassword = rootView.findViewById(R.id.changePasswordButton);
    }

    private void setValidations(){
        editPasswordActualValidation();
        editPasswordNuevaValidation();
        editPasswordConfirmarValidation();
    }

    private void editPasswordActualValidation(){
        String currentPassword = sessionManager.getContrasena();

        editPasswordActual.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();

                if (input.isEmpty()) {
                    errorPasswordActual.setVisibility(View.VISIBLE);
                    errorPasswordActual.setText(R.string.formError1);
                    isPasswordActualValid = false;
                } else if (!input.equals(currentPassword)) {
                    errorPasswordActual.setVisibility(View.VISIBLE);
                    errorPasswordActual.setText("La contraseña ingresada no es correcta");
                    isPasswordActualValid = false;
                } else {
                    errorPasswordActual.setVisibility(View.GONE);
                    isPasswordActualValid = true;
                }
                updateButtonState();
            }
        });
    }

    private void editPasswordNuevaValidation(){
        editPasswordNueva.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();

                if (input.isEmpty()) {
                    errorPasswordNueva.setVisibility(View.VISIBLE);
                    errorPasswordNueva.setText(R.string.formError1);
                    isPasswordNuevaValid = false;
                } else if (!passwordPattern.matcher(input).matches()) {
                    errorPasswordNueva.setVisibility(View.VISIBLE);
                    errorPasswordNueva.setText("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial");
                    isPasswordNuevaValid = false;
                } else {
                    errorPasswordNueva.setVisibility(View.GONE);
                    isPasswordNuevaValid = true;
                }
                updateButtonState();
            }
        });
    }

    private void editPasswordConfirmarValidation() {
        editPasswordConfirmar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();
                String nuevaPassword = editPasswordNueva.getText().toString().trim();

                if (input.isEmpty()) {
                    errorPasswordConfirmar.setVisibility(View.VISIBLE);
                    errorPasswordConfirmar.setText(R.string.formError1);
                    isPasswordConfirmarValid = false;
                } else if (!input.equals(nuevaPassword)) {
                    errorPasswordConfirmar.setVisibility(View.VISIBLE);
                    errorPasswordConfirmar.setText("Las contraseñas no coinciden");
                    isPasswordConfirmarValid = false;
                } else {
                    errorPasswordConfirmar.setVisibility(View.GONE);
                    isPasswordConfirmarValid = true;
                }
                updateButtonState();
            }
        });
    }

    private void updateButtonState() {
        boolean isFormValid = isPasswordActualValid &&
                isPasswordConfirmarValid &&
                isPasswordConfirmarValid;

        btnChangePassword.setEnabled(isFormValid);

        btnChangePassword.setAlpha(isFormValid ? 1.0f : 0.5f);
    }

    private boolean isFormCompleteAndValid() {
        return isPasswordActualValid &&
                isPasswordNuevaValid &&
                isPasswordConfirmarValid &&
                !editPasswordActual.getText().toString().trim().isEmpty() &&
                !editPasswordNueva.getText().toString().trim().isEmpty() &&
                !editPasswordConfirmar.getText().toString().trim().isEmpty();
    }

    private void setBtnChangePassword()
    {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFormCompleteAndValid()) {
                    Toast.makeText(requireContext(), "Por favor, completa correctamente todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                ChangePassRequest request = new ChangePassRequest(
                        editPasswordActual.getText().toString().trim(),
                        editPasswordNueva.getText().toString().trim()
                );

                BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(requireContext());
                BibliotecasViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(BibliotecasViewModel.class);

                viewModel.cambiarContrasena(request).observe(getViewLifecycleOwner(), response -> {
                    if (response != null) {
                        Toast.makeText(requireActivity(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity(), "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}