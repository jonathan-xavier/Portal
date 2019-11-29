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
import com.example.portala.adapter.AdapterTurma;
import com.example.portala.model.Turma;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TurmaInstituicaoActivity extends AppCompatActivity {
    private EditText campoAno, campoTurno;
    private ListView listViewTurma;
    private Turma turma;
    private List<Turma> adapterTurma = new ArrayList<>();
    private Button cadastrarTurma, listagemTurma;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_instituicao);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        inicializarComponentes();

        cadastrarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turma = new Turma();

                turma.setAnoTurma(campoAno.getText().toString());
                turma.setTurnoTurma(campoTurno.getText().toString());
                turma.setIdTurma(reference.push().getKey());

                reference.child("turma").child(turma.getIdTurma()).setValue(turma);

                Toast.makeText(TurmaInstituicaoActivity.this,
                        "Turma cadastrado.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        listagemTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapterTurma = new ArrayList<Turma>();

                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        adapterTurma.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            turma = new Turma();
                            turma = ds.getValue(Turma.class);
                            adapterTurma.add(turma);
                        }

                        AdapterTurma adapterTurma1 = new AdapterTurma(TurmaInstituicaoActivity.this, adapterTurma);
                        listViewTurma.setAdapter(adapterTurma1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                reference.child("turma").addValueEventListener(listener);

            }
        });

    }

    private void inicializarComponentes(){
        campoAno = findViewById(R.id.editTextAnoTurma);
        campoTurno = findViewById(R.id.editTextTurnoTurma);
        cadastrarTurma = findViewById(R.id.buttonCadastrarTurma);
        listagemTurma = findViewById(R.id.buttonListarTurma);
        listViewTurma = findViewById(R.id.listViewTurma);
    }

}