package com.example.admin.gipp_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.gipp_app.BancoLite.TarefaLiteDAO;
import com.example.admin.gipp_app.Connections.ConnectionListProjetos;
import com.example.admin.gipp_app.Connections.ConnectionListTarefas;
import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;

public class ListaTarefaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_lista);
        setSupportActionBar(toolbar);


        carregaListaDoBanco();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }


    public void carregaListaDoBanco() {
        Intent intent = getIntent();
        Projeto projetoID = (Projeto)intent.getSerializableExtra("projeto");

        TarefaLiteDAO Tdao = new TarefaLiteDAO(this);
        int PID = projetoID.getId();
        ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) Tdao.buscaTarefa(PID);

        final ListView TarefList = (ListView) findViewById(R.id.lista_de_tarefa);
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tarefas);
        TarefList.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaDoBanco();
    }


}
