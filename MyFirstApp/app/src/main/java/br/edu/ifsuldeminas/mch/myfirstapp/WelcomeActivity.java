package br.edu.ifsuldeminas.mch.myfirstapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity {

    private static  int PIC_CODE = 1;

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

        Button buttonTakePicture = findViewById(R.id.buttonTakePictureId);
        buttonTakePicture.setOnClickListener(view -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, PIC_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;
        if(data == null) return;

        if(requestCode == PIC_CODE){
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");

            ImageView imageView = findViewById(R.id.imageViewId);
            imageView.setImageBitmap(image);
        }
    }
}
