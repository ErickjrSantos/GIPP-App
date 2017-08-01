package com.example.admin.gipp_app.BancoLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 01/08/2017.
 */

public class TarefaLiteDAO extends SQLiteOpenHelper{
    public TarefaLiteDAO(Context context) {
        super(context, "Tarefas", null, 1);
    }

    private long id;
    private String nomeTarefa;
    private String data;
    private int concluido;
    private String prioridade;
    private String nomeCriador;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =    "CREATE TABLE Tarefas (id PRIMARY KEY," +
                        " nomeTarefa TEXT NOT NULL, data TEXT," +
                        " concluido TEXT, prioridade TEXT, nomeCriador TEXT);";
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
            dados.put("Nome",tarefa.getNome());
            dados.put("Prazo", String.valueOf(tarefa.getData()));
            dados.put("Descricao",tarefa.getDescricao());
            dados.put("Projeto",tarefa.getProjeto());
            dados.put("Concluido",tarefa.isConcluido());
            db.insert("Projetos", null, dados);
        }
    }
    private boolean existeNoBanco(long id){
        String sql = "SELECT id FROM Tarefas WHERE id=" +id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        return c.getCount()>0;
    }
    public List<Tarefa> buscaTarefa(){
        String sql = "SELECT * FROM Tarefa";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("id")));
            tarefa.setNome(c.getString(c.getColumnIndex("Nome")));
            tarefa.setData(c.getString(c.getColumnIndex("Prazo")));
            tarefa.setDescricao(c.getString(c.getColumnIndex("Descricao")));
            tarefa.setProjeto(c.getString(c.getColumnIndex("Projeto")));
            tarefa.setConcluido(c.getInt(c.getColumnIndex("Descricao")));

            tarefas.add(tarefa);
        }
        c.close();
        return tarefas;

    }
}
