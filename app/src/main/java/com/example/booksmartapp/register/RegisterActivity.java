package com.example.booksmartapp.register;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.R;
import com.example.booksmartapp.models.requests.RegisterRequest;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText editNombre, editApellido, editEmail, editPassword, editConfPassword, editCelular;
    private TextView errorNombre, errorApellido, errorCelular, errorEmail, errorPassword, errorConfPassword;
    private AutoCompleteTextView selectGender;
    private AppCompatButton btnRegistrar;
    private ScrollView scrollContainer;

    private boolean isDropdownOpen = false;
    private boolean isNameValid = false;
    private boolean isApellidoValid = false;
    private boolean isCelularValid = false;
    private boolean isCorreoValid = false;
    private boolean isPasswordValid = false;
    private boolean isConfPasswordValid = false;
    private boolean isGenderSelected = false;

    public retrofit2.Retrofit auth;

    private View errorDialogView;

    private Pattern namePattern = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñA-ZÁÉÍÓÚÑ ]{1,49}$");
    private Pattern passwordPattern = Pattern.compile("^((?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*#?&])).{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        errorDialogView = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.dialog_error_message, null);

        findViews();
        setGeneroValues();
        setValidaciones();
        setBtnRegistrar();

        updateButtonState();
    }

    private void findViews() {
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfPassword = findViewById(R.id.editConfPassword);
        editCelular = findViewById(R.id.editCelular);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        errorNombre = findViewById(R.id.errorNombre);
        errorApellido = findViewById(R.id.errorApellido);
        errorCelular = findViewById(R.id.errorCelular);
        errorEmail = findViewById(R.id.errorEmail);
        errorPassword = findViewById(R.id.errorPassword);
        errorConfPassword = findViewById(R.id.errorConfPassword);

        selectGender = findViewById(R.id.selectGender);
        scrollContainer = findViewById(R.id.scrollContainer);
    }

    private void setGeneroValues() {
        String[] generos = getResources().getStringArray(R.array.generos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                generos
        );
        selectGender.setAdapter(adapter);

        selectGender.setOnClickListener(v -> {
            if (isDropdownOpen) {
                selectGender.dismissDropDown();
            } else {
                selectGender.showDropDown();
            }
            isDropdownOpen = !isDropdownOpen;
        });

        selectGender.setOnItemClickListener((parent, view, position, id) -> {
            isDropdownOpen = false;
            isGenderSelected = !selectGender.getText().toString().trim().isEmpty();
            updateButtonState(); // Actualizar estado del botón
        });

        // Validar cambios en el texto del género
        selectGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isGenderSelected = !s.toString().trim().isEmpty();
                updateButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setValidaciones() {
        editNameValidation();
        editApellidoValidation();
        editCelularValidation();
        editCorreoValidation();
        editPasswordValidation();
        editConfPasswordValidation();
    }

    private void updateButtonState() {
        boolean isFormValid = isNameValid &&
                isApellidoValid &&
                isCelularValid &&
                isCorreoValid &&
                isPasswordValid &&
                isConfPasswordValid &&
                isGenderSelected;

        btnRegistrar.setEnabled(isFormValid);

        btnRegistrar.setAlpha(isFormValid ? 1.0f : 0.5f);
    }

    private void editNameValidation() {
        editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorNombre.setVisibility(View.VISIBLE);
                    errorNombre.setText(R.string.formError1);
                    isNameValid = false;
                } else if (!namePattern.matcher(input).matches()) {
                    errorNombre.setVisibility(View.VISIBLE);
                    errorNombre.setText("Debe comenzar con mayúscula y tener solo letras o espacios");
                    isNameValid = false;
                } else {
                    errorNombre.setVisibility(View.GONE);
                    isNameValid = true;
                }
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editApellidoValidation() {
        editApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorApellido.setVisibility(View.VISIBLE);
                    errorApellido.setText(R.string.formError1);
                    isApellidoValid = false;
                } else if (!namePattern.matcher(input).matches()) {
                    errorApellido.setVisibility(View.VISIBLE);
                    errorApellido.setText("Debe comenzar con mayúscula y tener solo letras o espacios");
                    isApellidoValid = false;
                } else {
                    errorApellido.setVisibility(View.GONE);
                    isApellidoValid = true;
                }
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editCelularValidation() {
        editCelular.addTextChangedListener(new TextWatcher() {
            Pattern phonePattern = Pattern.compile("^\\d{10}$");

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorCelular.setVisibility(View.VISIBLE);
                    errorCelular.setText(R.string.formError1);
                    isCelularValid = false;
                } else if (!phonePattern.matcher(input).matches()) {
                    errorCelular.setVisibility(View.VISIBLE);
                    errorCelular.setText("El número debe tener 10 dígitos obligatoriamente");
                    isCelularValid = false;
                } else {
                    errorCelular.setVisibility(View.GONE);
                    isCelularValid = true;
                }
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editCorreoValidation() {
        editEmail.addTextChangedListener(new TextWatcher() {
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorEmail.setVisibility(View.VISIBLE);
                    errorEmail.setText(R.string.formError1);
                    isCorreoValid = false;
                } else if (!emailPattern.matcher(input).matches()) {
                    errorEmail.setVisibility(View.VISIBLE);
                    errorEmail.setText("Ingrese un correo válido");
                    isCorreoValid = false;
                } else {
                    errorEmail.setVisibility(View.GONE);
                    isCorreoValid = true;
                }
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editPasswordValidation() {
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorPassword.setVisibility(View.VISIBLE);
                    errorPassword.setText(R.string.formError1);
                    isPasswordValid = false;
                } else if (!passwordPattern.matcher(input).matches()) {
                    errorPassword.setVisibility(View.VISIBLE);
                    errorPassword.setText("La contraseña debe contar con mínimo 8 caracteres, al menos una mayúscula, un número y un carácter especial");
                    isPasswordValid = false;
                } else {
                    errorPassword.setVisibility(View.GONE);
                    isPasswordValid = true; // CORREGIDO: estaba en false
                }

                // Re-validar confirmación de contraseña si ya tiene texto
                if (!editConfPassword.getText().toString().trim().isEmpty()) {
                    validatePasswordConfirmation();
                }
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
        });
    }

    private void editConfPasswordValidation() {
        editConfPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                validatePasswordConfirmation();
                updateButtonState(); // Actualizar estado del botón
            }

            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
        });
    }

    private void validatePasswordConfirmation() {
        String input = editConfPassword.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (input.isEmpty()) {
            errorConfPassword.setVisibility(View.VISIBLE);
            errorConfPassword.setText(R.string.formError1);
            isConfPasswordValid = false;
        } else if (!passwordPattern.matcher(input).matches()) {
            errorConfPassword.setVisibility(View.VISIBLE);
            errorConfPassword.setText("La contraseña debe contar con mínimo 8 caracteres, al menos una mayúscula, un número y un carácter especial");
            isConfPasswordValid = false;
        } else if (!input.equals(password)) {
            errorConfPassword.setVisibility(View.VISIBLE);
            errorConfPassword.setText("La contraseña no coincide");
            isConfPasswordValid = false;
        } else {
            errorConfPassword.setVisibility(View.GONE);
            isConfPasswordValid = true;
        }
    }

    private void setBtnRegistrar() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isFormCompleteAndValid()) {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa correctamente todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterRequest request = new RegisterRequest(
                        editNombre.getText().toString().trim(),
                        editApellido.getText().toString().trim(),
                        editEmail.getText().toString().trim(),
                        editCelular.getText().toString().trim(),
                        editPassword.getText().toString(),
                        selectGender.getText().toString().trim()
                );

                ViewModelProvider provider = new ViewModelProvider(RegisterActivity.this);
                AuthViewModel registerViewModel = provider.get(AuthViewModel.class);
                registerViewModel.getRegister(request).observe(RegisterActivity.this, usuarioResponse -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setView(errorDialogView);
                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView dialogTitle = errorDialogView.findViewById(R.id.dialogErrorTitle);
                    TextView dialogMessage = errorDialogView.findViewById(R.id.dialogErrorMessage);
                    Button btnAceptar = errorDialogView.findViewById(R.id.btnConfError);

                    if (usuarioResponse != null) {
                        String status = usuarioResponse.getStatus();
                        if(status.equals("Error al registrar usuario") || status.equals("Datos inválidos")) {
                            dialogTitle.setText("Error");
                            dialogMessage.setText(usuarioResponse.getMsg());
                            btnAceptar.setOnClickListener(v -> dialog.dismiss());
                            dialog.show();
                        }
                        else {
                            int id = usuarioResponse.getData().getUsuario_id();
                            Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
                            intent.putExtra("user_id", id);
                            startActivity(intent);
                        }
                    } else {

                        dialogTitle.setText("Error");
                        dialogMessage.setText("Hubo un problema al registrar. Intenta nuevamente.");
                        btnAceptar.setOnClickListener(v -> dialog.dismiss());
                        dialog.show();

                    }
                });
            }
        });
    }

    private boolean isFormCompleteAndValid() {
        return isNameValid &&
                isApellidoValid &&
                isCelularValid &&
                isCorreoValid &&
                isPasswordValid &&
                isConfPasswordValid &&
                isGenderSelected &&
                !editNombre.getText().toString().trim().isEmpty() &&
                !editApellido.getText().toString().trim().isEmpty() &&
                !editEmail.getText().toString().trim().isEmpty() &&
                !editCelular.getText().toString().trim().isEmpty() &&
                !editPassword.getText().toString().trim().isEmpty() &&
                !editConfPassword.getText().toString().trim().isEmpty() &&
                !selectGender.getText().toString().trim().isEmpty();
    }
}