package br.edu.ifsuldeminas.mch.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText userName;
    private EditText userPW;
    private static String USER = "Luiz";
    private static String PW = "123";
    private static final String TAG = "login_main_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Acessar elementos
        buttonLogin = findViewById(R.id.buttonLogin);
        userName = findViewById(R.id.editTextUser);
        userPW = findViewById(R.id.editTextNumberPassword);

        //Chamar o onClickListener
        buttonLogin.setOnClickListener(view -> {

            String userNameStr = userName.getText().toString();
            String userPWStr = userPW.getText().toString();

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

            if(userNameStr.equals(USER) && userPWStr.equals(PW)) {
                // Abrir uma tela/activity (Welcome)
                Intent welcomeIntent = new Intent(getApplicationContext(), WelcomeActivity.class);

                welcomeIntent.putExtra("user_name", userNameStr);

                startActivity(welcomeIntent);
            }else {
                Toast toast = Toast.makeText(view.getContext(), R.string.login_failure, Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        /*
        Log.i(TAG, "O método onCreate executou sem erros.");
        */
    }

    // Forma depreciada de implementação
    public void forgotPW (View view) {
        Toast toast = Toast.makeText(view.getContext(), R.string.login_button_forgot_pw_message, Toast.LENGTH_SHORT);
        toast.show();
    }
}