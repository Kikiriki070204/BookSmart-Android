package com.example.booksmartapp.datos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.BottomNavigationActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.requests.ChangePassRequest;
import com.example.booksmartapp.register.RegisterActivity;
import com.example.booksmartapp.register.VerifyActivity;
import com.example.booksmartapp.register.viewmodels.BibliotecaViewModelFactory;
import com.example.booksmartapp.select.viewmodels.BibliotecasViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class CambiarPasswordActivity extends AppCompatActivity {

    private TextInputEditText editPasswordActual, editPasswordNueva, editPasswordConfirmar;
    private TextView errorPasswordActual, errorPasswordNueva, errorPasswordConfirmar;

    private boolean isPasswordActualValid = false;
    private boolean isPasswordNuevaValid = false;
    private boolean isPasswordConfirmarValid = false;
    private MaterialButton btnChangePassword;
    private Pattern passwordPattern = Pattern.compile("^((?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*#?&])).{8,}$");

    private View settingsMenu;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cambiar_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        setValidations();
        setUpSettings();
        setBottomNavigation();

        setBtnChangePassword();
        updateButtonState();
    }

    private void findViews(){
        editPasswordActual = findViewById(R.id.editPasswordActual);
        editPasswordNueva = findViewById(R.id.editPasswordNueva);
        editPasswordConfirmar = findViewById(R.id.editConfPasswordNueva);
        errorPasswordActual = findViewById(R.id.errorPasswordActual);
        errorPasswordNueva = findViewById(R.id.errorPasswordNueva);
        errorPasswordConfirmar = findViewById(R.id.errorConfPassword);

        settingsMenu = findViewById(R.id.settingsPassword);
        bottomNavigation = findViewById(R.id.bottomNavigationPassword);
        btnChangePassword = findViewById(R.id.changePasswordButton);
    }

    private void setValidations(){
        editPasswordActualValidation();
        editPasswordNuevaValidation();
        editPasswordConfirmarValidation();
    }

    private void editPasswordActualValidation(){
        SessionManager sessionManager = SessionManager.getInstance();
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

    private void setUpSettings() {
        TextView userDataName = settingsMenu.findViewById(R.id.userDataName);
        ImageButton menuIcon = settingsMenu.findViewById(R.id.menuIcon);

        SessionManager sessionManager = SessionManager.getInstance();
        userDataName.setText(sessionManager.getUsuario(this).getNombre());

        menuIcon.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, menuIcon);
            popup.getMenuInflater().inflate(R.menu.settings_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.userData) {
                    startActivity(new Intent(this, DatosUsuarioActivity.class));
                    return true;
                } else if (id == R.id.change_pass) {
                    startActivity(new Intent(this, CambiarPasswordActivity.class));
                    return true;
                } else if (id == R.id.logout) {
                    // lógica de logout
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    private void setBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, BottomNavigationActivity.class));
                return true;
            } else if (itemId == R.id.nav_search) {
                Intent intent = new Intent(this, BottomNavigationActivity.class);
                intent.putExtra("destino", "search");
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }

    private void setBtnChangePassword()
    {
     btnChangePassword.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (!isFormCompleteAndValid()) {
                 Toast.makeText(CambiarPasswordActivity.this, "Por favor, completa correctamente todos los campos", Toast.LENGTH_SHORT).show();
                 return;
             }

             ChangePassRequest request = new ChangePassRequest(
                     editPasswordActual.getText().toString().trim(),
                     editPasswordNueva.getText().toString().trim()
             );

             BibliotecaViewModelFactory factory = new BibliotecaViewModelFactory(CambiarPasswordActivity.this);
             BibliotecasViewModel viewModel = new ViewModelProvider(CambiarPasswordActivity.this, factory).get(BibliotecasViewModel.class);

             viewModel.cambiarContrasena(request).observe(CambiarPasswordActivity.this, response -> {
                 if (response != null) {
                     Toast.makeText(CambiarPasswordActivity.this, "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(CambiarPasswordActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                 }
             });
         }
     });
    }

}