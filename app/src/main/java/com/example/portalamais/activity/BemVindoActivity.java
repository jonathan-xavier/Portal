package com.example.portalamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.portalamais.R;

public class BemVindoActivity extends AppCompatActivity {

    private Button criarConta,logar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);
        getSupportActionBar().hide();
        criarConta = findViewById(R.id.buttonBemVindoCriarConta);
        logar = findViewById(R.id.buttonBemvindoLogar);

        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CadastroUsuarioActivity.class));

            }
        });

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginAutenticacaoActivity.class));
            }
        });





    }
}
