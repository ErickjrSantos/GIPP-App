package com.example.admin.gipp_app.Modelo;

import java.io.Serializable;

/**
 * Created by admin on 29/07/2017.
 */

public class Projeto implements Serializable{

    private  int id;
    private   String nomeProjeto;
    private  int quantTarefas;
    private  double progresso;

    public  String getNomeProjeto() {return nomeProjeto;}

    public  int getId() {return id;}

    public  void setId(int id) {this.id = id;}

    public  void setNomeProjeto(String nomeProjeto) {this.nomeProjeto = nomeProjeto;}

    public  int getQuantTarefas() {return quantTarefas;}

    public  void setQuantTarefas(int quantTarefas) {this.quantTarefas = quantTarefas;}

    public  double getProgresso() {return progresso;}

    public  void setProgresso(double progresso) {this.progresso = progresso;}

    @Override
    public String toString() {
        return getId() + " - " + getNomeProjeto() + " - " + (getProgresso() * 100) + "%" ;
    }
}
