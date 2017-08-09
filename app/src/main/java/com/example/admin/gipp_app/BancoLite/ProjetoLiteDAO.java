package com.example.admin.gipp_app.BancoLite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.gipp_app.Connections.ConnectionListProjetos;
import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 29/07/2017.
 */

public class ProjetoLiteDAO extends SQLiteOpenHelper {
    public ProjetoLiteDAO(Context context) {
        super(context, "Projetos", null, 1);

    }

    private long id;
    private String nomeProjeto;
    private int quantTarefas;
    private int progresso;
    private String status;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Projetos (id PRIMARY KEY, nomeProjeto TEXT NOT NULL, quantTarefas TEXT, progresso TEXT, status TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Projetos";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insereProjeto(Projeto projeto) {
        SQLiteDatabase db = getWritableDatabase();
        if (!existeNoBanco(projeto.getId())) {
            ContentValues dados = new ContentValues();
            dados.put("id", projeto.getId());
            dados.put("nomeProjeto", projeto.getNomeProjeto());
            dados.put("quantTarefas", projeto.getQuantTarefas());
            dados.put("progresso", projeto.getProgresso());
            dados.put("status", projeto.getStatus());
            db.insert("Projetos", null, dados);
        }
    }

    public void insereProjeto(ArrayList<Projeto> projetos) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = 0; i < projetos.size(); i++) {
            Projeto projeto = projetos.get(i);
             if (!existeNoBanco(projeto.getId())){
                ContentValues dados = new ContentValues();
                dados.put("id", projeto.getId());
                dados.put("nomeProjeto", projeto.getNomeProjeto());
                dados.put("quantTarefas", projeto.getQuantTarefas());
                dados.put("progresso", projeto.getProgresso());
                dados.put("status", projeto.getStatus());
                db.insert("Projetos", null, dados);
            }
        }

    }

    private boolean existeNoBanco(int id) {
        String sql = "SELECT id FROM Projetos WHERE id=" + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        return c.getCount() > 0;
    }



    public List<Projeto> buscaProjetos() {

            String sql = "SELECT * FROM Projetos WHERE status='Em andamento';";

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(sql, null);
            List<Projeto> projetos = new ArrayList<>();


            while (c.moveToNext()) {

                try {

                    Projeto projeto = new Projeto();
                    projeto.setId(c.getInt(c.getColumnIndex("id")));
                    projeto.setNomeProjeto(c.getString(c.getColumnIndex("nomeProjeto")));
                    projeto.setQuantTarefas(c.getInt(c.getColumnIndex("quantTarefas")));
                    projeto.setProgresso(c.getDouble(c.getColumnIndex("progresso")));
                    projeto.setStatus(c.getString(c.getColumnIndex("status")));

                    projetos.add(projeto);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            c.close();
            return projetos;
    }
    
    public List<Projeto> buscaProjetosPendentes() {
           String sql = "SELECT * FROM Projetos WHERE status='Pendente de Aprovação'";

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(sql, null);
            List<Projeto> projetos = new ArrayList<>();


            while (c.moveToNext()) {

                try {

                    Projeto projeto = new Projeto();
                    projeto.setId(c.getInt(c.getColumnIndex("id")));
                    projeto.setNomeProjeto(c.getString(c.getColumnIndex("nomeProjeto")));
                    projeto.setQuantTarefas(c.getInt(c.getColumnIndex("quantTarefas")));
                    projeto.setProgresso(c.getDouble(c.getColumnIndex("progresso")));
                    projeto.setStatus(c.getString(c.getColumnIndex("status")));

                    projetos.add(projeto);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            c.close();
            return projetos;

    }

}
