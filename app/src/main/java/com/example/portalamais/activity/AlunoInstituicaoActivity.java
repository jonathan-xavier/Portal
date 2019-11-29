package com.example.portalamais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portalamais.R;
import com.example.portalamais.adapter.AdapterAluno;
import com.example.portalamais.model.Aluno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AlunoInstituicaoActivity extends AppCompatActivity {

    private ListView listViewAluno;
    private EditText editTextNome, editTextMatricula;
    private TextView textViewNome, textViewMatricula;
    private Button buttonCadastrarAluno;
    private Aluno aluno;
    private ArrayList<Aluno> adapterAluno;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_instituicao);
        setTitle("Aluno");

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        listViewAluno = findViewById(R.id.listViewAluno);
        editTextNome = findViewById(R.id.editTextNomeAluno);
        editTextMatricula = findViewById(R.id.editTextMatriculaAluno);
        textViewNome = findViewById(R.id.textViewLabelNomeAluno);
        textViewMatricula = findViewById(R.id.textViewLabelMatriculaAluno);
        buttonCadastrarAluno = findViewById(R.id.buttonCadastrarAluno);

        buttonCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno = new Aluno();

                aluno.setNomeAluno(editTextNome.getText().toString());
                aluno.setMatriculaAluno(Integer.parseInt(editTextMatricula.getText().toString()));
                aluno.setIdAluno(reference.push().getKey());

                reference.child("Aluno").child(aluno.getIdAluno()).setValue(aluno);

                Toast.makeText(AlunoInstituicaoActivity.this,
                        "Aluno cadastrado com sucesso!",
                        Toast.LENGTH_SHORT).show();

            }
        });

        listViewAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterAluno = new ArrayList<Aluno>();

                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        adapterAluno.clear();

                        for(DataSnapshot ds : dataSnapshot.getChildren()){

                            aluno = new Aluno();

                            aluno = ds.getValue(Aluno.class);

                            adapterAluno.add(aluno);

                        }

                        AdapterAluno adapter = new AdapterAluno(getApplicationContext(), adapterAluno);
                        listViewAluno.setAdapter(adapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };


                reference.child("aluno").addValueEventListener(listener);


            }
        });

    }
}
