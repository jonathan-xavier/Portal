package com.example.portalamais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.portalamais.R;

public class HomeInstituicaoActivity extends AppCompatActivity {

    private Button buttonAluno;
    private Button buttonEvento;
    private Button buttonTurma;
    private Button buttonDisciplina;
    private Button buttonNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_instituicao);

        inicializarComponentes();

        buttonAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AlunoInstituicaoActivity.class));
            }
        });

        buttonEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventoInstituicaoActivity.class));
            }
        });

        buttonTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TurmaInstituicaoActivity.class));
            }
        });

        buttonDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DisciplinaInstituicaoActivity.class));
            }
        });

        buttonNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotaInstituicaoActivity.class));
            }
        });


    }

    private void inicializarComponentes(){
        buttonAluno = findViewById(R.id.buttonAluno);
        buttonEvento = findViewById(R.id.buttonEvento);
        buttonTurma = findViewById(R.id.buttonTurma);
        buttonDisciplina = findViewById(R.id.buttonDisciplina);
        buttonNota = findViewById(R.id.buttonNota);
    }

}
