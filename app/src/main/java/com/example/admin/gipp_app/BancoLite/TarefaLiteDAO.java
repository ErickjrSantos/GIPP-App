package com.example.admin.gipp_app.BancoLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 01/08/2017.
 */

public class TarefaLiteDAO extends SQLiteOpenHelper{
    private static final String teste = "meu teste";
    private static final String erro = "meu teste";

    public TarefaLiteDAO(Context context) {
        super(context, "Tarefas", null, 2);
    }

    private long id;
    private String nomeTarefa;
    private String data;
    private String descricao;
    private int concluido;
    private String prioridade;
    private int projeto;
    private String nomeCriador;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =    "CREATE TABLE Tarefas (id PRIMARY KEY, nomeTarefa TEXT NOT NULL, data TEXT,descricao TEXT, projeto TEXT, prioridade TEXT ,concluido TEXT, nomeCriador TEXT);";
                        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersin, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Tarefas";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insereTarefa(Tarefa tarefa){
        SQLiteDatabase db = getWritableDatabase();
        if(!existeNoBanco(tarefa.getId())) {
            ContentValues dados = new ContentValues();
            dados.put("id", tarefa.getId());
            dados.put("nomeTarefa", tarefa.getNome());
            dados.put("data", String.valueOf(tarefa.getData()));
            dados.put("descricao", tarefa.getDescricao());
            dados.put("projeto", tarefa.getProjetoid());
            dados.put("Concluido", tarefa.isConcluido());
            dados.put("prioridade", tarefa.getPrioridade());
            dados.put("nomeCriador", tarefa.getNomeCriador());
            db.insert("Projetos", null, dados);

        }
    }
    public void insereTarefa(ArrayList<Tarefa> tarefas){
        SQLiteDatabase db = getWritableDatabase();
        try {

            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i); //*


                if (!existeNoBanco(tarefa.getProjetoid())) {
                    ContentValues dados = new ContentValues();
                    dados.put("id", tarefa.getId());
                    dados.put("nomeTarefa", tarefa.getNome());
                    dados.put("data", tarefa.getData());
                    dados.put("descricao", tarefa.getDescricao());
                    dados.put("projeto", tarefa.getProjetoid());
                    dados.put("Concluido", tarefa.isConcluido());
                    dados.put("prioridade", tarefa.getPrioridade());
                    dados.put("nomeCriador", tarefa.getNomeCriador());
                    db.insert("Tarefas", null, dados);


                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private boolean existeNoBanco(long id){
        String sql = "SELECT id FROM Tarefas WHERE id=" +id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        return c.getCount()>0;
    }

    public List<Tarefa> buscaTarefa(int projeto){
        try {
            String sql = "SELECT * FROM Tarefas WHERE projeto=" +projeto;

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(sql, null);
            List<Tarefa> tarefas = new ArrayList<>();
            while (c.moveToNext()) {
                Tarefa tarefa = new Tarefa();

                tarefa.setId(c.getLong(c.getColumnIndex("id")));
                tarefa.setNome(c.getString(c.getColumnIndex("nomeTarefa")));
                tarefa.setData(c.getString(c.getColumnIndex("data")));
                tarefa.setDescricao(c.getString(c.getColumnIndex("descricao")));
                tarefa.setProjetoid(c.getInt(c.getColumnIndex("projeto")));
                tarefa.setConcluido(c.getInt(c.getColumnIndex("concluido")));
                tarefa.setPrioridade(c.getInt(c.getColumnIndex("prioridade")));
                tarefa.setNomeCriador(c.getString(c.getColumnIndex("nomeCriador")));

                tarefas.add(tarefa);
            }
            c.close();
            return tarefas;
        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
    }
}
