package com.example.admin.gipp_app.Connections;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.gipp_app.BancoLite.ProjetoLiteDAO;
import com.example.admin.gipp_app.MainActivity;
import com.example.admin.gipp_app.Modelo.Login;
import com.example.admin.gipp_app.Modelo.Projeto;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 29/07/2017.
 */

public class ConnectionListProjetos extends AsyncTask {

    String url = "http://187.35.128.157:70/Android/projetos.php";
    public ArrayList< String > AL = new ArrayList<>();
    //public ArrayList<Projeto> projetos = new ArrayList<>();

    StringBuffer response = new StringBuffer();

    @Override
    protected List<Projeto> doInBackground(Object[] objects) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            String urlParameters = "userid=" + objects[0];

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
            if (JsonStr != null || JsonStr != "") {
                try {

                    JSONObject jsonObjt = new JSONObject(JsonStr);
                    int quantidade = jsonObjt.getInt("quantidade");
                    JSONArray jsonArray = jsonObjt.getJSONArray("projetos");

                    Projeto projeto = new Projeto();

                    for (int i = 0; i < quantidade; i++) {

                        int id = jsonArray.getJSONObject(i).getInt("id");
                        String nomeProjeto = jsonArray.getJSONObject(i).getString("nomeProjeto");
                        int quantTarefa = jsonArray.getJSONObject(i).getInt("quantTarefas");
                        double progresso = jsonArray.getJSONObject(i).getDouble("progresso");

                        projeto.setId(id);
                        projeto.setNomeProjeto(nomeProjeto);
                        projeto.setQuantTarefas(quantTarefa);
                        projeto.setProgresso(progresso);

                        //projetos.add(projeto);

                        //funciona
                        AL.add(projeto.toString());

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
