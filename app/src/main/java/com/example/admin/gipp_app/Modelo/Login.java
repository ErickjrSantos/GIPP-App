package com.example.admin.gipp_app.Modelo;

/**
 * Created by admin on 26/07/2017.
 */

public class Login{
    String id;
    String nome;
    String resposta;
    private static Login instance;

    public static Login setInstance(String id, String nome, String resposta){
        instance = getInstance();
        instance.setId(id);
        instance.setNome(nome);
        instance.setResposta(resposta);
        return instance;
    }

    public static Login getInstance(){
        if(instance == null)
            instance = new Login();
        return instance;
    }

    public String getId() { return id; }
    private void setId(String id) { this.id = id; }

    public String getNome() {
        return nome;
    }
    public Boolean getResposta() {
        return resposta.equals("success");
    }

    private void setNome(String nome) {
        this.nome = nome;
    }


    public void setResposta(String resposta) {this.resposta = resposta; }
}
