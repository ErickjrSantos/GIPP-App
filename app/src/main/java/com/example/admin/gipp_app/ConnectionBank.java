package com.example.admin.gipp_app;

import android.util.Log;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Created by Admin on 15/07/2017.
 */


public class ConnectionBank {
    private java.sql.Connection conn = null;
    private Statement st;
    private ResultSet rs;
    private String sql;

    public void connect(){
       String porta = "3306";
        String host = "192.168.0.221";
        String banco = "pegpese";
        String usuario = "admin";
        String senha = "admin";

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();


        } catch(Exception erro){
            Log.e("MYSQL","Erro: "+erro);
        }

        try{
            conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+porta+"/"+banco+"?connectTimeout=9000&socketTimeout=9000&autoReconnect=true&secondsBeforeRetryMaster=3",usuario,senha);
            Log.i("MYSQL", "Conectado.");
        } catch(Exception erro){
            Log.e("MYSQL","Erro: "+erro);
        }
    }


}
