package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;


import com.example.admin.gipp_app.Modelo.LoginDAO;

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

    String url = "http://192.168.0.221:70/Android/login.php";




    @Override
    protected Object doInBackground(Object[] objects) {


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
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            String JsonStr = response.toString();
            if(JsonStr != null){
                try{
                    LoginDAO login = new LoginDAO();
                    JSONObject jsonObjt = new JSONObject(JsonStr);
                    String id = jsonObjt.getString("id");
                   String nome = jsonObjt.getString("nome");

                    login.getInstance(id,nome);



                }catch (Exception e){
                    e.printStackTrace();
                }
            }






            in.close();
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
