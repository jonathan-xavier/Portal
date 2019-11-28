package com.example.portalamais.model;

import java.util.ArrayList;

public class Nota {

    private String id;
    private float primeiroBimestre ;
    private float segundoBimestre;
    private float terceiroBimestre;
    private float quartoBimestre;

    private float mediaGeral;
    private float notaRecuperacao;
    private float mediaFinal;
    private Aluno aluno;
    private ArrayList<Disciplina> disciplina;

    public Nota(){
        this.aluno = new Aluno();
        this.disciplina = new ArrayList<>();
    }

    public void cadastrarNota(){

    }
    public void alterarNota(){

    }
    public void removerNota(){

    }

    public String listarNota(String nota){
        return nota;
    }

}
