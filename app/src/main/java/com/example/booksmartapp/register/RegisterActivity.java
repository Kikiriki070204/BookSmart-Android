package com.example.booksmartapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

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
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.example.booksmartapp.retrofit.auth_request;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatEditText editNombre, editApellido, editEmail, editPassword, editConfPassword, editCelular;
    private AppCompatButton btnRegistrar;

    public retrofit2.Retrofit auth;
    void setAuth() {
        auth = auth_request.getRetrofit();
    }

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

        btnRegistrar.setOnClickListener(onClick -> {
            Intent intent = new Intent(this, BottomNavigationActivity.class);
            startActivity(intent);
        });
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
    }

    /*
    private boolean validateFields()
    {
        String name = editNombre.getText().toString().trim();
        String lastName = editApellido.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confPassword = editConfPassword.getText().toString().trim();
        String phone = editCelular.getText().toString().trim();

    }
     */

    private void setBtnRegistrar()
    {
        RegisterRequest request =  new RegisterRequest(
                editNombre.getText().toString().trim(),
                editApellido.getText().toString().trim(),
                editEmail.getText().toString().trim(),
                editPassword.getText().toString(),
                editCelular.getText().toString().trim()
        );
        //if(!validateFields())
        btnRegistrar.setOnClickListener(onClick -> {
            ViewModelProvider provider = new ViewModelProvider(this);
            AuthViewModel registerViewModel = provider.get(AuthViewModel.class);
            registerViewModel.getRegister(request);
        });
    }

}