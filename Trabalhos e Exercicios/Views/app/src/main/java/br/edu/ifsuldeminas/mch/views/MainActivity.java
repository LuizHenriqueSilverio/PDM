package br.edu.ifsuldeminas.mch.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button constraintLayout = findViewById(R.id.button);
    Button netflix = findViewById(R.id.button2);

    Button melhorTexto = findViewById(R.id.button3);

    public void onClickConstraint(View v) {
        Intent intent = new Intent();
    }
}