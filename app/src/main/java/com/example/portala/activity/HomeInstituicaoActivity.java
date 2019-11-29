package com.example.portala.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.portala.R;

public class HomeInstituicaoActivity extends AppCompatActivity {

    private Button aluno, evento, turma, disciplina, nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_instituicao);

        aluno = findViewById(R.id.buttonAluno);
        evento = findViewById(R.id.buttonEvento);
        turma = findViewById(R.id.buttonTurma);
        disciplina = findViewById(R.id.buttonDisciplina);
        nota = findViewById(R.id.buttonNota);

        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AlunoInstituicaoActivity.class));
            }
        });

        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventoInstituicaoActivity.class));
            }
        });

        turma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TurmaInstituicaoActivity.class));
            }
        });

        disciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DisciplinaInstituicaoActivity.class));
            }
        });

        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotaInstituicaoActivity.class));
            }
        });

    }
}
