package com.example.portala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portala.R;
import com.example.portala.model.Aluno;

import java.util.List;

public class AdapterAluno extends ArrayAdapter {

    List<Aluno> lista;
    TextView labelNome,nomeAluno,labelMatricula,matriculaAluno;

    public AdapterAluno(Context context, List<Aluno> dados){

        super(context, R.layout.adapter_lista_aluno,dados);

        lista = dados;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.adapter_lista_aluno,null);

        labelNome = v.findViewById(R.id.textViewLabelNomeAluno);
        nomeAluno = v.findViewById(R.id.textViewNomeAluno);
        labelMatricula = v.findViewById(R.id.textViewLabelMatriculaAluno);
        matriculaAluno = v.findViewById(R.id.textViewMatriculaAluno);

        labelNome.getText();
        nomeAluno.setText(lista.get(position).getNomeAluno());
        labelMatricula.getText();
        matriculaAluno.setText(lista.get(position).getMatriculaAluno());


        return v;
    }
}
