package com.example.portalamais.Class;

public class Responsavel {
    private String id;
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    private String senha;
    private String email;
    private String foto;
    //falta list de alunos e obj instituicao

    public Responsavel(){

    }

    public void cadastrar(){

    }
    public void alterar(){

    }
    public void remover(){

    }

    public String listar(String lista){
        return  lista;
    }

}
