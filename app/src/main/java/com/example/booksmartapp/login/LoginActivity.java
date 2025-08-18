package com.example.booksmartapp.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.MessageActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.requests.LoginRequest;
import com.example.booksmartapp.register.RegisterActivity;
import com.example.booksmartapp.register.VerifyActivity;
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.example.booksmartapp.responses.ErrorResponse;
import com.example.booksmartapp.select.SelectLibraryActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText editCorreo, editPassword;
    private TextView errorCorreo, errorPassword;
    private boolean isCorreoValid, isPasswordValid;
    private MaterialButton btnLogin;

    private View errorDialogView, dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        errorDialogView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_error_message, null);
        dialogView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_message, null);
        findViews();
        setValidations();
        //HomePrueba();
        setBtnLogin();
        updateButtonState();
    }

    private void findViews()
    {
        editCorreo = findViewById(R.id.editCorreoLogin);
        editPassword = findViewById(R.id.editPasswordLogin);
        btnLogin = findViewById(R.id.btnInicioSesion);

        errorCorreo = findViewById(R.id.errorCorreoLogin);
        errorPassword = findViewById(R.id.errorPasswordLogin);
    }

    private void setValidations(){
        editCorreoValidation();
        editPasswordValidation();
    }
    private void editCorreoValidation()
    {
        editCorreo.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();

                if (input.isEmpty()) {
                    errorCorreo.setVisibility(View.VISIBLE);
                    errorCorreo.setText(R.string.formError1);
                    isCorreoValid = false;
                }  else {
                    errorCorreo.setVisibility(View.GONE);
                    isCorreoValid = true;
                }
                updateButtonState();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    private void editPasswordValidation()
    {
        editPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();

                if (input.isEmpty()) {
                    errorPassword.setVisibility(View.VISIBLE);
                    errorPassword.setText(R.string.formError1);
                    isPasswordValid = false;
                }  else {
                    errorPassword.setVisibility(View.GONE);
                    isPasswordValid = true;
                }
                updateButtonState();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    private void updateButtonState() {
        boolean isFormValid = isCorreoValid &&
                isPasswordValid;

        btnLogin.setEnabled(isFormValid);

        btnLogin.setAlpha(isFormValid ? 1.0f : 0.5f);
    }

    private void HomePrueba()
    {
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, SelectLibraryActivity.class);
               startActivity(intent);
           }
       });
    }

    private void setBtnLogin()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isFormCompleteAndValid()) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa correctamente todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginRequest request = new LoginRequest(
                        editCorreo.getText().toString().trim(),
                        editPassword.getText().toString().trim()
                );

                ViewModelProvider provider = new ViewModelProvider(LoginActivity.this);
                AuthViewModel loginViewModel = provider.get(AuthViewModel.class);
                loginViewModel.getLogin(request).observe(LoginActivity.this, loginResponseApiResponse -> {
                    if (loginResponseApiResponse != null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setView(errorDialogView);
                        AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        TextView dialogTitle = errorDialogView.findViewById(R.id.dialogErrorTitle);
                        TextView dialogMessage = errorDialogView.findViewById(R.id.dialogErrorMessage);
                        Button btnAceptar = errorDialogView.findViewById(R.id.btnConfError);

                        if(loginResponseApiResponse.getStatus().equals("Datos inválidos") ||
                                loginResponseApiResponse.getStatus().equals("Error del servidor") ||
                                loginResponseApiResponse.getStatus().equals("Error de autenticación")) {
                            dialogTitle.setText("Error");
                            dialogMessage.setText(loginResponseApiResponse.getMsg());
                            btnAceptar.setOnClickListener(v -> dialog.dismiss());
                            dialog.show();
                        }
                        else if(loginResponseApiResponse.getStatus().equals("El Usuario no tiene bibliotecas vinculadas")) {

                            builder.setView(dialogView);
                            AlertDialog dialogGreen = builder.create();
                            dialogGreen.setCancelable(false);
                            dialogGreen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            TextView dialogTitleGreen = dialogGreen.findViewById(R.id.dialogTitle);
                            TextView dialogMessageGreen = dialogGreen.findViewById(R.id.dialogMessage);
                            Button btnAceptarGreen = dialogGreen.findViewById(R.id.btnConf);

                            dialogTitleGreen.setText("Regístrate en una Biblioteca");
                            dialogMessageGreen.setText(loginResponseApiResponse.getMsg());
                            btnAceptarGreen.setOnClickListener(v -> dialogGreen.dismiss());
                            dialogGreen.show();
                        }
                        SessionManager sessionManager = SessionManager.getInstance();
                        sessionManager.saveToken(LoginActivity.this, loginResponseApiResponse.getData().getToken());
                        sessionManager.saveContrasena(LoginActivity.this,editPassword.getText().toString().trim());
                        sessionManager.setUser(loginResponseApiResponse.getData().getUser());
                        Intent intent = new Intent(LoginActivity.this, SelectLibraryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private boolean isFormCompleteAndValid() {
        return isCorreoValid && isPasswordValid &&
                !editCorreo.getText().toString().trim().isEmpty()
                && !editPassword.getText().toString().trim().isEmpty();
    }
}