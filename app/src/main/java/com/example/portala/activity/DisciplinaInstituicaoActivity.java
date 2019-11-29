package com.example.portala.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.portala.R;
import com.example.portala.adapter.AdapterDisciplina;
import com.example.portala.model.Disciplina;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaInstituicaoActivity extends AppCompatActivity {
    private EditText campoNome;
    private ListView listViewDisciplina;
    private Disciplina disciplina;
    private List<Disciplina> adapterDisciplina = new ArrayList<>();
    private Button cadastrarDisciplina, listagemDisciplina;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina_instituicao);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        inicializarComponentes();

        cadastrarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disciplina = new Disciplina();

                disciplina.setNomeDisciplina(campoNome.getText().toString());
                disciplina.setIdDisciplina(reference.push().getKey());

                reference.child("disciplina").child(disciplina.getIdDisciplina()).setValue(disciplina);

                Toast.makeText(DisciplinaInstituicaoActivity.this,
                        "Disciplina cadastrado.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        listagemDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapterDisciplina = new ArrayList<Disciplina>();

                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        adapterDisciplina.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            disciplina = new Disciplina();
                            disciplina = ds.getValue(Disciplina.class);
                            adapterDisciplina.add(disciplina);
                        }

                        AdapterDisciplina adapterTurma1 = new AdapterDisciplina(DisciplinaInstituicaoActivity.this, adapterDisciplina);
                        listViewDisciplina.setAdapter(adapterTurma1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                reference.child("disciplina").addValueEventListener(listener);

            }
        });

    }

    private void inicializarComponentes(){
        campoNome = findViewById(R.id.editTextNomeDisciplina);
        cadastrarDisciplina = findViewById(R.id.buttonCadastrarDisciplina);
        listagemDisciplina = findViewById(R.id.buttonListarDisciplina);
        listViewDisciplina = findViewById(R.id.listViewDisciplina);
    }

}