package com.example.admin.gipp_app.BancoLite;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.admin.gipp_app.Connections.ConnectionListProjetos;
import com.example.admin.gipp_app.Modelo.Projeto;



/**
 * Created by admin on 29/07/2017.
 */

public class ProjetoLiteDAO extends SQLiteOpenHelper {
    public ProjetoLiteDAO(ConnectionListProjetos context){
        super(context,"Projetos",null, 1);

    }
    private  int id;
    private  String nomeProjeto;
    private  int    quantTarefas;
    private  int    progresso;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Projetos (id PRIMARY KEY, nomeProjeto TEXT NOT NULL, quantTarefas TEXT, progresso TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Projetos";
        db.execSQL(sql);
        onCreate(db);

    }
    public  void insereProjeto(Projeto projeto){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("id",Projeto.getId());
        dados.put("nomeProjeto",Projeto.getNomeProjeto());
        dados.put("quantTarefas",Projeto.getQuantTarefas());
        dados.put("progresso",Projeto.getProgresso());
        db.insert("Projetos",null,dados);

    }

}
