package com.example.portalamais.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.portalamais.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirLogin();
            }
        }, 2000);

    }

    private void abrirLogin(){
        Intent intent = new Intent(SplashActivity.this, BemVindoActivity.class);
        startActivity(intent);
        finish();
    }


}
