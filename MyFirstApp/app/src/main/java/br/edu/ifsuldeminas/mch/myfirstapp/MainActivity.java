package br.edu.ifsuldeminas.mch.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Acessar elementos
        Button buttonLogin = findViewById(R.id.buttonLogin);
        EditText userName = findViewById(R.id.editTextUser);
        EditText userPW = findViewById(R.id.editTextNumberPassword);

        String userNameStr = userName.getText().toString();
        String userPWStr = userPW.getText().toString();

        //Chamar o onClickListener
        buttonLogin.setOnClickListener(view -> {
            if(userNameStr.equals("")) {
                Toast toast = Toast.makeText(view.getContext(), R.string.login_user_name_empty, Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if(userPWStr.equals("")) {
                Toast toast = Toast.makeText(view.getContext(), R.string.login_user_pw_empty, Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        });


    }

    // Forma depreciada de implementação
    public void forgotPW (View view) {
        Toast toast = Toast.makeText(view.getContext(), R.string.login_button_forgot_pw_message, Toast.LENGTH_SHORT);
        toast.show();
    }
}