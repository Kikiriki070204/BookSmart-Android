package com.example.booksmartapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.booksmartapp.login.LoginActivity;

public class MessageActivity extends AppCompatActivity {
    private View errorDialogView, dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dialogView = LayoutInflater.from(MessageActivity.this).inflate(R.layout.dialog_message, null);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            showDialog();
        }, 2000);
    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
        builder.setView(dialogView);
        builder.setView(dialogView);
        AlertDialog dialogGreen = builder.create();
        dialogGreen.setCancelable(false);
        dialogGreen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView dialogTitleGreen = dialogGreen.findViewById(R.id.dialogTitle);
        TextView dialogMessageGreen = dialogGreen.findViewById(R.id.dialogMessage);
        Button btnAceptarGreen = dialogGreen.findViewById(R.id.btnConf);

        dialogTitleGreen.setText("Regístrate en una Biblioteca");
        dialogMessageGreen.setText("Para hacer uso del servicio debes estar \nregistrado en una de las bibliotecas \nafiliadas");
        btnAceptarGreen.setOnClickListener(v -> {
            dialogGreen.dismiss();
            Intent intent = new Intent(MessageActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        dialogGreen.show();
    }
}