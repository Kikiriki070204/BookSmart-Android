package com.example.booksmartapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.RegisterRequest;
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.example.booksmartapp.retrofit.auth_request;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText editNombre, editApellido, editEmail, editPassword, editConfPassword, editCelular;

    private TextView errorNombre, errorApellido, errorCelular, errorEmail, errorPassword, errorConfPassword;

    private AutoCompleteTextView selectGender;

    private AppCompatButton btnRegistrar;
    private boolean isDropdownOpen = false;
    private boolean isNameValid = false;
    private boolean isApellidoValid = false;
    private boolean isCelularValid = false;
    private boolean isCorreoValid = false;
    private boolean isPasswordValid = false;
    private boolean isConfPasswordValid = false;
    private boolean isFormValid;

    public retrofit2.Retrofit auth;
    void setAuth() {
        auth = auth_request.getRetrofit();
    }

    private Pattern namePattern = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñA-ZÁÉÍÓÚÑ ]{1,49}$");
    private Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*)(?=.*[@$!%*#?&])[A-Za-z@$!%*#?&]{8,}$");

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
        findViews();
        setGeneroValues();
        setValidaciones();
        setBtnRegistrar();
    }

    private void findViews()
    {
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
        });
    }

    private void setValidaciones()
    {
        editNameValidation();
        editApellidoValidation();
        editCelularValidation();
        editCorreoValidation();
        editPasswordValidation();
        editConfPasswordValidation();
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
                validateForm();
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }
    private void editApellidoValidation()
    {
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
                validateForm();
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editCelularValidation()
    {
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
                    isCelularValid = false;
                }
                validateForm();
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }
    private void editCorreoValidation()
    {
        editEmail.addTextChangedListener(new TextWatcher() {
            Pattern emailPattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
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
                validateForm();
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void editPasswordValidation()
    {
        editPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
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
                    isPasswordValid = false;
                }
                validateForm();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    private void editConfPasswordValidation()
    {
        editConfPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                String input = s.toString().trim();

                if (input.isEmpty()) {
                    errorConfPassword.setVisibility(View.VISIBLE);
                    errorConfPassword.setText(R.string.formError1);
                    isConfPasswordValid = false;
                } else if (!passwordPattern.matcher(input).matches()) {
                    errorConfPassword.setVisibility(View.VISIBLE);
                    errorConfPassword.setText("La contraseña debe contar con mínimo 8 caracteres, al menos una mayúscula, un número y un carácter especial");
                    isConfPasswordValid = false;
                } else if (!input.equals(editConfPassword.getText().toString().trim())){
                    errorConfPassword.setVisibility(View.VISIBLE);
                    errorConfPassword.setText("La contraseña no coincide");
                    isConfPasswordValid = true;
                }
                else
                {
                    errorEmail.setVisibility(View.GONE);
                }
                validateForm();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    private void validateForm() {
        isFormValid = isNameValid && isApellidoValid && isCelularValid &&
                isCorreoValid && isPasswordValid && isConfPasswordValid;
    }
    private void setBtnRegistrar()
    {
        RegisterRequest request =  new RegisterRequest(
                editNombre.getText().toString().trim(),
                editApellido.getText().toString().trim(),
                editEmail.getText().toString().trim(),
                editPassword.getText().toString(),
                editCelular.getText().toString().trim(),
                selectGender.getText().toString().trim()

        );
        if(!isFormValid)
        {
            btnRegistrar.setEnabled(isFormValid);
            btnRegistrar.setAlpha(isFormValid ? 1f : 0.5f);
        }
        else{
            btnRegistrar.setEnabled(isFormValid);
            btnRegistrar.setAlpha(1f);
            btnRegistrar.setOnClickListener(onClick -> {
                Intent intent = new Intent(this, BottomNavigationActivity.class);
                startActivity(intent);
            });
        }
        /*
        btnRegistrar.setOnClickListener(onClick -> {
            ViewModelProvider provider = new ViewModelProvider(this);
            AuthViewModel registerViewModel = provider.get(AuthViewModel.class);
            registerViewModel.getRegister(request);
        });
         */
    }

}