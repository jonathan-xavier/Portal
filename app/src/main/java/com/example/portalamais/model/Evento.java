package com.example.portalamais.model;

public class Evento {

    private String id;
    private String titulo;
    private String descricao;
    private Instituicao instituicao;

    public Evento(){
        this.instituicao  = new Instituicao();

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
