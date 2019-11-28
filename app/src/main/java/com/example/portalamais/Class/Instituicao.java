package com.example.portalamais.Class;

import java.util.ArrayList;


public class Instituicao {


    private String senha;
    private ArrayList<Responsavel> responsavel;
    private ArrayList<Evento> evento;
    private Aluno aluno;

    private String id;
    private String email;



    public Instituicao(){
       /* this.responsavel= new ArrayList<>();
        this.evento = new ArrayList<>();
        this.aluno = new Aluno();*/
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }






    public void cadastrar(){

    }
    public void alterar(){

    }
}
