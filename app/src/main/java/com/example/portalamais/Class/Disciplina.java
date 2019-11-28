package com.example.portalamais.Class;

import java.util.ArrayList;

public class Disciplina {

    private String id;
    private String nome;
    private Nota nota;
    private ArrayList<Turma> turma;

    public Disciplina(){
        this.nota = new Nota();
        this.turma = new ArrayList<>();
    }

    public void cadastrar(){

    }
    public void alterar(){

    }
    public void remover(){

    }
    public String listar(String lista){
        return lista;
    }
}
