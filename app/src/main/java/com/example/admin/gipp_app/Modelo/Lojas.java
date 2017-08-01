package com.example.admin.gipp_app.Modelo;

import java.io.Serializable;

/**
 * Created by admin on 01/08/2017.
 */

public class Lojas implements Serializable {
    private int id;
    private String nome;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    @Override
    public String toString() {
        return getId()+"   " +getNome()+";)" ;
    }
}
