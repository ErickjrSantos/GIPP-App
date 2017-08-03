package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;

import com.example.admin.gipp_app.Modelo.Tarefa;

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

public class ConnectionListTarefas extends AsyncTask {

    String url = "http://187.35.128.157:70/Android/tarefasProjeto.php";

    public ArrayList<Tarefa> tarefas = new ArrayList<>();

    StringBuffer response = new StringBuffer();

    @Override
    protected ArrayList<Tarefa> doInBackground(Object[] objects) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            String urlParameters = "pcod=" + objects[0];

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
                    JSONArray jsonArray = jsonObjt.getJSONArray("tarefas");


                    for (int i = 0; i < quantidade; i++) {
                        Tarefa tarefa = new Tarefa();

                        int id = jsonArray.getJSONObject(i).getInt("codigoT");
                        String nomeTarefa = jsonArray.getJSONObject(i).getString("nomeTarefa");
                        String data = jsonArray.getJSONObject(i).getString("data");
                        int concluido = jsonArray.getJSONObject(i).getInt("concluido");
                        int prioridade = jsonArray.getJSONObject(i).getInt("prioridade");
                        String descricao = jsonArray.getJSONObject(i).getString("descricao");
                        String nomeCriador = jsonArray.getJSONObject(i).getString("nomeCriadorT");


                        tarefa.setId(id);
                        tarefa.setNome(nomeTarefa);
                        tarefa.setData(data);
                        tarefa.setConcluido(concluido);
                        tarefa.setPrioridade(prioridade);
                        tarefa.setDescricao(descricao);
                        tarefa.setNomeCriador(nomeCriador);

                        tarefas.add(i, tarefa);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    return tarefas;
    }
}