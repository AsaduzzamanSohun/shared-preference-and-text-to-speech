package com.example.readit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    TextView tvDisplay;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        tvDisplay = findViewById(R.id.tv_display);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        tvDisplay.setText(sharedPreferences.getString("text", ""));
    }
}