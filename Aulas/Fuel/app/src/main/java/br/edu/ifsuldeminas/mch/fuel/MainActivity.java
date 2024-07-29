package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private SharedPreferences preferences;
    private static final String PREFS_KEY = "br.edu.ifsuldeminas.mch.fuel.prefs";
    private static final String GAS_KEY = "price_gas";
    private static final String ETHANOL_KEY = "price_ethanol";

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();

        String gasPrice = textInputEditTextGas.getText().toString();
        String ethanolPrice = textInputEditTextEthanol.getText().toString();

        if(!gasPrice.equals("")) {
            editor.putString(GAS_KEY, gasPrice);
        }

        if(!ethanolPrice.equals("")) {
            editor.putString(ETHANOL_KEY, ethanolPrice);
        }

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(preferences.contains(GAS_KEY)) {
            String gasPrice = preferences.getString(GAS_KEY, "");
            textInputEditTextGas.setText(gasPrice);
        }

        if(preferences.contains(ETHANOL_KEY)) {
            String ethanolPrice = preferences.getString(ETHANOL_KEY, "");
            textInputEditTextEthanol.setText(ethanolPrice);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);

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

        imageViewShare.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Preços de qual posto?");

            LayoutInflater inflater = getLayoutInflater();
            View layoutDialogView = inflater.inflate(R.layout.alert_dialog_gas_station_view, null);

            builder.setView(layoutDialogView);

            builder.setNegativeButton("Cancelar", null);

            builder.setPositiveButton("Compartilhar", (dialogLayout, button) -> {
                EditText editText = layoutDialogView.findViewById(R.id.editTextAlertDialogId);
                String posto = editText.getText().toString();

                if(posto.equals("")) {
                    Toast toast = Toast.makeText(this, "Nome do posto não pode ser vazio!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                String message = String.format
                        ("Preços do posto '%s'. Gasolina %.2f, Etanol: %.2f. %s Relação de: %.2f%s",
                                posto, gasPrice, ethanolPrice, tip, (ethanolPrice/gasPrice) * 100, "%");

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "");
                startActivity(shareIntent);
            });

            builder.create().show();
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