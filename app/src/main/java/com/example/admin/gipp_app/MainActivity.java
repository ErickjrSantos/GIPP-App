package com.example.admin.gipp_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.gipp_app.BancoLite.ProjetoLiteDAO;
import com.example.admin.gipp_app.BancoLite.TarefaLiteDAO;
import com.example.admin.gipp_app.Calendario.CalendarActivity;
import com.example.admin.gipp_app.Connections.ConnectionListProjetos;
import com.example.admin.gipp_app.Connections.ConnectionListTarefas;
import com.example.admin.gipp_app.Connections.ConnectionLojas;
import com.example.admin.gipp_app.Modelo.Login;
import com.example.admin.gipp_app.Modelo.Lojas;
import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConnectionListProjetos prolist = new ConnectionListProjetos();
        Login lg = Login.getInstance();
        prolist.execute(lg.getId());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProFormulario = new Intent(MainActivity.this,FormularioActivity.class);
                startActivity(vaiProFormulario);
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void carregaBanco(){
        ProjetoLiteDAO dao = new ProjetoLiteDAO(this);
        ConnectionListProjetos CLP = new ConnectionListProjetos();
        TarefaLiteDAO Tdao = new TarefaLiteDAO(this);
        Login id = Login.getInstance();

        CLP.execute(id.getId());
        ArrayList<Projeto> Projetos = CLP.projetos;
        ArrayList<Tarefa> Tarefas = CLP.tarefas;
        dao.insereProjeto(Projetos);
        Tdao.insereTarefa(Tarefas);



    }

    public void carregaLista(){
        ProjetoLiteDAO dao = new ProjetoLiteDAO(this);
        ArrayList<Projeto> Projetos = (ArrayList<Projeto>) dao.buscaProjetos();

        final ListView ProjectList = (ListView) findViewById(R.id.listaDeProjetos);
        ArrayAdapter<Projeto> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Projetos);
        ProjectList.setAdapter(arrayAdapter);
        ProjectList.setBackgroundColor(1);

        ProjectList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Projeto projeto = (Projeto)ProjectList.getItemAtPosition(position);
                try {
                    Intent vaiPraListaDeTarefa = new Intent(MainActivity.this, ListaTarefaActivity.class);
                    vaiPraListaDeTarefa.putExtra("projeto", projeto);
                    startActivity(vaiPraListaDeTarefa);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaBanco();
        carregaLista();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_calendario) {
            Intent vaiProCalendario = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(vaiProCalendario);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
