package br.edu.ifsuldeminas.mch.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SimpleContract extends ActivityResultContract<String, String> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String input) {
        Intent intent = new Intent(context, WelcomeActivity.class);

        intent.putExtra("user_name", input);

        return intent;
    }

    @Override
    public String parseResult(int resultCode, @Nullable Intent intent) {
        String result = "";

        if(resultCode == Activity.RESULT_OK) {
            result = intent.getStringExtra("result_welcome");
        }

        return result;
    }
}
