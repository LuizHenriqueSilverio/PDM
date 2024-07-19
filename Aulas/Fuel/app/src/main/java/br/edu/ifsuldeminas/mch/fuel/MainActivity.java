package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEthanol;
    private TextInputEditText textInputEditTextGas;
    private Button buttonCalculate;
    private ImageView imageViewResult;
    private ImageView imageViewShare;
    private TextView textViewResult;
    private String tip;
    private Double ethanolPrice, gasPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditTextEthanol = findViewById(R.id.textInputEditTextEtanol);
        textInputEditTextGas = findViewById(R.id.textInputEditTextGas);
        buttonCalculate = findViewById(R.id.buttonCalcular);
        imageViewResult = findViewById(R.id.imageViewFuel);
        imageViewShare = findViewById(R.id.imageViewShare);
        textViewResult = findViewById(R.id.textViewMessage);

        buttonCalculate.setOnClickListener(view -> {
            String ethanolPriceStr = textInputEditTextEthanol.getText().toString();
            String gasPriceStr = textInputEditTextGas.getText().toString();

            if (ethanolPriceStr.equals("")) {
                Toast.makeText(getApplicationContext(), "O valor do etanol não foi informado!", Toast.LENGTH_LONG).show();
                return;
            }

            if (gasPriceStr.equals("")) {
                Toast.makeText(getApplicationContext(), "O valor da gasolina não foi informado!", Toast.LENGTH_LONG).show();
                return;
            }

            ethanolPrice = Double.parseDouble(ethanolPriceStr);
            gasPrice = Double.parseDouble(gasPriceStr);

            if(ethanolPrice / gasPrice < 0.7) {
                imageViewResult.setImageResource(R.drawable.ethanol);
                tip = "Melhor usar etanol!";
            } else {
                imageViewResult.setImageResource(R.drawable.gas);
                tip = "Melhor usar gasolina!";
            }

            textViewResult.setText(tip);

            imageViewResult.setVisibility(ImageView.VISIBLE);
            imageViewShare.setVisibility(ImageView.VISIBLE);
            textViewResult.setVisibility(TextView.VISIBLE);
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        imageViewResult.setVisibility(ImageView.INVISIBLE);
        imageViewShare.setVisibility(ImageView.INVISIBLE);
        textViewResult.setVisibility(TextView.INVISIBLE);
    }
}