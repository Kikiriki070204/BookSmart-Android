package com.example.booksmartapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.booksmartapp.MessageActivity;
import com.example.booksmartapp.R;
import com.example.booksmartapp.models.SessionManager;
import com.example.booksmartapp.models.requests.VerifyRequest;
import com.example.booksmartapp.register.viewmodels.AuthViewModel;
import com.example.booksmartapp.responses.ApiResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class VerifyActivity extends AppCompatActivity {

    private TextInputEditText editCode;
    private MaterialButton btnValidar;
    private TextView errorCode;
    private boolean isCodeValid;

    private int temporalId;
    private Pattern codePattern = Pattern.compile("^[A-Za-z0-9]{16,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        temporalId = SessionManager.getUser_id();
        findViews();
        editCodeValidation();
        setBtnValidar();

        updateButtonState();
    }

    private void findViews()
    {
        editCode = findViewById(R.id.editCode);
        btnValidar = findViewById(R.id.btnValidar);
        errorCode = findViewById(R.id.errorCode);
    }

    private void editCodeValidation()
    {
        editCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString().trim();

                if (input.isEmpty()) {
                    errorCode.setVisibility(View.VISIBLE);
                    errorCode.setText(R.string.formError1);
                    isCodeValid = false;
                } else if (!codePattern.matcher(input).matches()) {
                    errorCode.setVisibility(View.VISIBLE);
                    errorCode.setText("El código debe tener al menos 16 caracteres");
                    isCodeValid = false;
                } else {
                    errorCode.setVisibility(View.GONE);
                    isCodeValid = true;
                }
                updateButtonState();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }

    private void updateButtonState() {
        boolean isFormValid = isCodeValid;
        btnValidar.setEnabled(isFormValid);
        btnValidar.setAlpha(isFormValid ? 1.0f : 0.5f);
    }

    private boolean isFormCompleteAndValid() {
        return isCodeValid &&
                !editCode.getText().toString().trim().isEmpty();
    }

    private void setBtnValidar()
    {
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyRequest request = new VerifyRequest(
                        editCode.getText().toString().trim()
                );

                if (!isFormCompleteAndValid()) {
                    Toast.makeText(VerifyActivity.this, "Por favor, completa correctamente todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                ViewModelProvider provider = new ViewModelProvider(VerifyActivity.this);
                AuthViewModel verifyViewModel = provider.get(AuthViewModel.class);
                verifyViewModel.getVerify(temporalId,request).observe(VerifyActivity.this, apiResponse -> {
                    if(apiResponse != null)
                    {
                        startActivity(new Intent(VerifyActivity.this, MessageActivity.class));
                    }
                    else
                    {
                        Toast.makeText(VerifyActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                } );

            }
        });
    }
}