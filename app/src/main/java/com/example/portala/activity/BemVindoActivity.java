package com.example.portala.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.portala.R;

public class BemVindoActivity extends AppCompatActivity {

    private Button cadastro;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        getSupportActionBar().hide();

        cadastro = findViewById(R.id.buttonBemVindoCriarConta);
        login = findViewById(R.id.buttonBemvindoLogar);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BemVindoActivity.this, CadastroActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BemVindoActivity.this, LoginActivity.class));
            }
        });

    }
}
