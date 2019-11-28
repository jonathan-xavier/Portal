package com.example.portalamais.model;

import java.util.ArrayList;

public class Aluno {


    private String nome;
    private int ano;
    private Responsavel responsavel;
    //private Turma turma;
    private Instituicao instituicao;
    private ArrayList<Nota> notas;

    private String id;


    public Aluno(){
       /* this.responsavel = new Responsavel();
       // this.turma = new Turma();
        this.instituicao = new Instituicao();
        this.notas = new ArrayList<>();*/
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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
