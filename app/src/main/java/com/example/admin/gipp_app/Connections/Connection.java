package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;

import com.example.admin.gipp_app.Modelo.Login;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by admin on 25/07/2017.
 */


public class Connection extends AsyncTask {

    String url = "http://187.35.128.157:70/Android/login.php";

    @Override
    protected Object doInBackground(Object[] objects) {

        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");


            //dados POST
            String urlParameters = "user=" + objects[0] + "&password=" + objects[1];


            //Cria POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            int responseCode = con.getResponseCode();
            System.out.println("Codigo de resposta: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String JsonStr = response.toString();
            if(JsonStr != null || JsonStr != ""){
                try{

                    JSONObject jsonObjt = new JSONObject(JsonStr);
                    String id = jsonObjt.getString("codigo");
                    String nome = jsonObjt.getString("nome");
                    String resposta = jsonObjt.getString("resposta");

                    Login user = Login.setInstance(id,nome,resposta);

                    return user;

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
}
