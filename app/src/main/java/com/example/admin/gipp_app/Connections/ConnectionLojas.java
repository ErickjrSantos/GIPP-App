package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;

import com.example.admin.gipp_app.Modelo.Login;
import com.example.admin.gipp_app.Modelo.Lojas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 01/08/2017.
 */

public class ConnectionLojas extends AsyncTask {
    String url = "http://187.35.128.157:70/Android/unidades.php";
    public ArrayList<Lojas> lojas = new ArrayList<>();
    @Override
    protected Object doInBackground(Object[] objects) {


        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");




            //Cria POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
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
                    int quantidade = jsonObjt.getInt("quantidade");
                    JSONArray jsonArray = jsonObjt.getJSONArray("unidades");

                    for(int i = 0; i < quantidade; i++) {
                        Lojas loja = new Lojas();


                        int id = jsonArray.getJSONObject(i).getInt("id");
                        String nomeUnidade = jsonArray.getJSONObject(i).getString("nomeUnidade");


                        loja.setId(id);
                        loja.setNome(nomeUnidade);

                        lojas.add(i, loja);
                    }
                    return lojas;
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return lojas;
    }


}

