package com.example.portala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portala.R;
import com.example.portala.model.Disciplina;

import java.util.List;

public class AdapterDisciplina extends ArrayAdapter {

    List<Disciplina> lista;
    TextView labelNomeDisciplina,nomeDisciplina, labelId, idDisciplina;

    public AdapterDisciplina(Context context, List<Disciplina> dados){

        super(context, R.layout.adapter_lista_disciplina,dados);

        lista = dados;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.adapter_lista_disciplina,null);

        labelNomeDisciplina = v.findViewById(R.id.textViewNomeDisciplina);
        nomeDisciplina = v.findViewById(R.id.textViewNomeDisciplina);
        labelId = v.findViewById(R.id.textViewLabelIdDisciplina);
        idDisciplina = v.findViewById(R.id.textViewIdDisciplina);

        labelNomeDisciplina.getText();
        nomeDisciplina.setText(lista.get(position).getNomeDisciplina());
        labelId.getText();
        idDisciplina.setText(lista.get(position).getIdDisciplina());

        return v;
    }
}
