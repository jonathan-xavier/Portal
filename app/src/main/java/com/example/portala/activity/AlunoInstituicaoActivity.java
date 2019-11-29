package com.example.portala.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.portala.R;
import com.example.portala.adapter.AdapterAluno;
import com.example.portala.model.Aluno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlunoInstituicaoActivity extends AppCompatActivity {

    private EditText campoNome, campoMatricula;
    private ListView listViewAluno;
    private Aluno aluno;
    private List<Aluno> adapterAluno = new ArrayList<>();
    private Button cadastrarAluno, listagemAluno;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_instituicao);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        inicializarComponentes();

        cadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aluno = new Aluno();

                aluno.setNomeAluno(campoNome.getText().toString());
                aluno.setMatriculaAluno(campoMatricula.getText().toString());
                aluno.setIdAluno(reference.push().getKey());

                reference.child("aluno").child(aluno.getIdAluno()).setValue(aluno);

                Toast.makeText(AlunoInstituicaoActivity.this,
                        "Aluno cadastrado.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        listagemAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapterAluno = new ArrayList<Aluno>();

                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        adapterAluno.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            aluno = new Aluno();
                            aluno = ds.getValue(Aluno.class);
                            adapterAluno.add(aluno);
                        }

                        AdapterAluno adapterAluno1 = new AdapterAluno(AlunoInstituicaoActivity.this, adapterAluno);
                        listViewAluno.setAdapter(adapterAluno1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                reference.child("aluno").addValueEventListener(listener);

            }
        });

    }

    private void inicializarComponentes(){
        campoNome = findViewById(R.id.editTextNomeAluno);
        campoMatricula = findViewById(R.id.editTextMatriculaAluno);
        cadastrarAluno = findViewById(R.id.buttonCadastrarAluno);
        listagemAluno = findViewById(R.id.buttonListarAluno);
        listViewAluno = findViewById(R.id.listViewAluno);
    }

}
