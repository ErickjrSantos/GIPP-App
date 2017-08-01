package com.example.admin.gipp_app.Modelo;

/**
 * Created by admin on 19/07/2017.
 */

public class Tarefa {
    private long id;
    private String nome;
    private String data;
    private String projeto;
    private int concluido;
    private String descricao;
    private int prioridade;
    private String nomeCriador;

    public Tarefa(){

    }
    public Tarefa(String nome, String projeto,int concluido){
        super();
        this.nome = nome;
        this.projeto = projeto;
        this.concluido = concluido;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int isConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCriador() {return nomeCriador;}

    public void setNomeCriador(String nomeCriador) {this.nomeCriador = nomeCriador;}

    public int getPrioridade() {return prioridade;}

    public void setPrioridade(int prioridade) {this.prioridade = prioridade;}

}

