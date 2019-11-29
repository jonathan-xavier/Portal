package com.example.portala.model;

import java.util.ArrayList;

public class Instituicao {

    private String idInstituicao;
    private String nomeInstituicao;
    private String emaiInstituicao;
    private String senhaInstituicao;
    private ArrayList<Responsavel> responsavel;
    private ArrayList<Evento> evento;
    private ArrayList<Aluno> aluno;

    public Instituicao() {
        this.responsavel = new ArrayList<>();
        this.evento = new ArrayList<>();
        this.aluno = new ArrayList<>();
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(String idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getEmaiInstituicao() {
        return emaiInstituicao;
    }

    public void setEmaiInstituicao(String emaiInstituicao) {
        this.emaiInstituicao = emaiInstituicao;
    }

    public String getSenhaInstituicao() {
        return senhaInstituicao;
    }

    public void setSenhaInstituicao(String senhaInstituicao) {
        this.senhaInstituicao = senhaInstituicao;
    }

    public ArrayList<Responsavel> getResponsavel() {
        return responsavel;
    }

    public ArrayList<Evento> getEvento() {
        return evento;
    }

    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

}
