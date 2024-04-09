package br.edu.ifsuldeminas.mch.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Registrar o layout
        setContentView(R.layout.activity_welcome);

        Intent intentCall = getIntent();
        String userName = intentCall.getStringExtra("user_name");

        View layout = findViewById(R.id.activity_welcome_id);
        Snackbar snackbar = Snackbar.make(layout, "Bem vindo " + userName, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
