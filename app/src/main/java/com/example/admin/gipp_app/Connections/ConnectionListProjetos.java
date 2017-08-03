package com.example.admin.gipp_app.Connections;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.gipp_app.BancoLite.ProjetoLiteDAO;
import com.example.admin.gipp_app.MainActivity;
import com.example.admin.gipp_app.Modelo.Evento;
import com.example.admin.gipp_app.Modelo.Login;
import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

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

    String url = "http://187.35.128.157:70/Android/geralProjeto.php";

    public ArrayList<Projeto> projetos = new ArrayList<>();
    public ArrayList<Tarefa> tarefas = new ArrayList<>();
    public ArrayList<Evento> eventos = new ArrayList<>();

    StringBuffer response = new StringBuffer();

    @Override
    protected ArrayList<Projeto> doInBackground(Object[] objects) {
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
                    int quantidade = jsonObjt.getInt("quantProj");

                    JSONArray jsonArrayP = jsonObjt.getJSONArray("projetos");


                    for (int i = 0; i < quantidade; i++) {
                        Projeto projeto = new Projeto();

                        int pid =             jsonArrayP.getJSONObject(i).getInt("pcodigo");
                        String nomeProjeto = jsonArrayP.getJSONObject(i).getString("nomeProjeto");
                        int quantTarefa =    jsonArrayP.getJSONObject(i).getInt("quantTarefas");
                        double progresso =   jsonArrayP.getJSONObject(i).getDouble("progresso");


                        projeto.setId(pid);
                        projeto.setNomeProjeto(nomeProjeto);
                        projeto.setQuantTarefas(quantTarefa);
                        projeto.setProgresso(progresso);

                        projetos.add(i, projeto);

                        JSONArray jsonArrayT = jsonArrayP.getJSONObject(i).getJSONArray("tarefas");

                        try {


                            for (int j = 0; j < quantTarefa; j++) {
                                Tarefa tarefa = new Tarefa();


                                int idT = jsonArrayT.getJSONObject(j).getInt("tcodigo");
                                String nomeTarefa = jsonArrayT.getJSONObject(j).getString("nomeTarefa");
                                String data = jsonArrayT.getJSONObject(j).getString("data");
                                int concluido = jsonArrayT.getJSONObject(j).getInt("concluido");
                                int prioridade = jsonArrayT.getJSONObject(j).getInt("prioridade");
                                int quantEventos = jsonArrayT.getJSONObject(j).getInt("quantEventos");
                                String descricao = jsonArrayT.getJSONObject(j).getString("descTarefa");
                                String nomeCriador = jsonArrayT.getJSONObject(j).getString("criadorTarefa");


                                tarefa.setId(idT);
                                tarefa.setNome(nomeTarefa);
                                tarefa.setData(data);
                                tarefa.setConcluido(concluido);
                                tarefa.setPrioridade(prioridade);
                                tarefa.setDescricao(descricao);
                                tarefa.setNomeCriador(nomeCriador);
                                tarefa.setQuantEventos(quantEventos);
                                tarefa.setProjetoid(pid);

                                tarefas.add(j, tarefa);
                                JSONArray jsonArrayE = jsonArrayT.getJSONObject(j).getJSONArray("eventos");
                                try {
                                    for (int x = 0; x < quantEventos; x++) {
                                        Evento evento = new Evento();
                                        int ecodigo = jsonArrayE.getJSONObject(x).getInt("ecodigo");
                                        String eventoT = jsonArrayE.getJSONObject(x).getString("evento");
                                        String dataHora = jsonArrayE.getJSONObject(x).getString("dataHora");
                                        String criadorEvento = jsonArrayE.getJSONObject(x).getString("criadorEvento");

                                        evento.setEcodigo(ecodigo);
                                        evento.setEvento(eventoT);
                                        evento.setDataHora(dataHora);
                                        evento.setCriadorEvento(criadorEvento);
                                        eventos.add(x, evento);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
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
