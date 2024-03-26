package br.edu.ifsuldeminas.mch.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Acessar o botão
        Button buttonLogin = findViewById(R.id.buttonLogin);

        //Chamar o onClickListener
        buttonLogin.setOnClickListener(view -> {
            Toast toast = Toast.makeText(view.getContext(), R.string.login_successful, Toast.LENGTH_SHORT);
            toast.show();
        });

        //Passar uma View.OnClickListener
        //Implementar uma subclasse de View.OnClickListener
    }
}