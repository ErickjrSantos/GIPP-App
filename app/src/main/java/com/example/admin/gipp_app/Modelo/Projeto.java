package com.example.admin.gipp_app.Modelo;

import java.io.Serializable;

/**
 * Created by admin on 29/07/2017.
 */

public class Projeto implements Serializable{

    private static int id;
    private static  String nomeProjeto;
    private static int quantTarefas;
    private static double progresso;



    public static String getNomeProjeto() {return nomeProjeto;}

    public static int getId() {return id;}

    public static void setId(int id) {Projeto.id = id;}

    public static void setNomeProjeto(String nomeProjeto) {Projeto.nomeProjeto = nomeProjeto;}

    public static int getQuantTarefas() {return quantTarefas;}

    public static void setQuantTarefas(int quantTarefas) {Projeto.quantTarefas = quantTarefas;}

    public static double getProgresso() {return progresso;}

    public static void setProgresso(double progresso) {Projeto.progresso = progresso;}

    @Override
    public String toString() {
        return getId() +" - "+getNomeProjeto()+ "     " + getProgresso() ;
    }
}
