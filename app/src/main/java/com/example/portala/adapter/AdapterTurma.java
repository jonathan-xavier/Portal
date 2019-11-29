package com.example.portala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.portala.R;
import com.example.portala.model.Turma;

import java.util.List;

public class AdapterTurma extends ArrayAdapter {

    List<Turma> lista;
    TextView labelAno,anoTurma,labelTurno,turnoTurma;

    public AdapterTurma(Context context, List<Turma> dados){

        super(context, R.layout.adapter_lista_turma,dados);

        lista = dados;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.adapter_lista_turma,null);

        labelAno = v.findViewById(R.id.textViewLabelAnoTurma);
        anoTurma = v.findViewById(R.id.textViewAnoTurma);
        labelTurno = v.findViewById(R.id.textViewLabelTurnoTurma);
        turnoTurma = v.findViewById(R.id.textViewTurnoTurma);

        labelAno.getText();
        anoTurma.setText(lista.get(position).getAnoTurma());
        labelTurno.getText();
        turnoTurma.setText(lista.get(position).getTurnoTurma());


        return v;
    }
}

