package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView compte = findViewById(R.id.compte);
        Button welcome = (Button) findViewById(R.id.welcome);

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                welcome.setClickable(false);
                compte.setText("" + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                welcome.setClickable(true);
                compte.setText("Appuyer nimporte où pour commencer");
            }
        }.start();


        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(v.getContext(), MainActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
    }
}