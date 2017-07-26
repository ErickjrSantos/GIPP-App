package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by admin on 25/07/2017.
 */

public class Connection extends AsyncTask {


    public String login(String User, String Password) {

        String url = "http://192.168.0.221:70/Android/login.php";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters = "user=" + User + "&password=" + Password;

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
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
           return e.toString();
        }

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
