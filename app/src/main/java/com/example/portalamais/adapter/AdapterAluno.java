package com.example.portalamais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portalamais.R;
import com.example.portalamais.model.Aluno;
import com.example.portalamais.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAluno extends ArrayAdapter {

    List<Aluno> lista;
    TextView matriculaAluno,nomeAluno,labelMatriculaAluno,labelNomeAluno;

    public AdapterAluno(Context context,List<Aluno> dados){

        super(context, R.layout.adapter_lista_aluno,dados);

        lista = dados;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.adapter_lista_aluno,null);

        matriculaAluno = v.findViewById(R.id.textViewMatriculaAluno);
        nomeAluno = v.findViewById(R.id.textViewNomeAluno);
        labelMatriculaAluno = v.findViewById(R.id.textViewLabelMatriculaAluno);
        labelNomeAluno = v.findViewById(R.id.textViewLabelMatriculaAluno);

        matriculaAluno.setText(lista.get(position).getMatriculaAluno());
        nomeAluno.setText(lista.get(position).getNomeAluno());
        labelMatriculaAluno.getText();
        labelNomeAluno.getText();

        return v;
    }

}