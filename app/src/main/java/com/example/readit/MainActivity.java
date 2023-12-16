package com.example.readit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.window.OnBackInvokedDispatcher;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textField;
    FloatingActionButton speakerBtn;
    AppCompatButton saveBtn, viewBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.text_field);
        speakerBtn = findViewById(R.id.speaker_btn);
        saveBtn = findViewById(R.id.save_btn);
        viewBtn = findViewById(R.id.next_btn);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textField.setText(sharedPreferences.getString("text", ""));

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myText = textField.getText().toString();
                editor.putString("text", myText);
                editor.apply();


            }
        });

        speakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myText = textField.getText().toString();
                textToSpeech.speak(String.valueOf(myText), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, NewActivity.class));

            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit")
                .setMessage("Are you sure want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAndRemoveTask();

                    }
                })
                .show();
    }
}