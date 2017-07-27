package com.example.admin.gipp_app.Modelo;

/**
 * Created by admin on 26/07/2017.
 */

public class LoginDAO {
    String id;
    String nome;
    private LoginDAO instance;

    public LoginDAO getInstance(String id, String nome){
        if(instance == null)
            instance = new LoginDAO();
        instance.setId(id);
        instance.setNome(nome);
        return instance;
    }

    public LoginDAO getInstance(){
        if(instance == null)
            instance = new LoginDAO();
        return instance;
    }

    public String getId() { return id; }
    private void setId(String id) { this.id = id; }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }








}
