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
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.gipp_app.BancoLite.ProjetoLiteDAO;
import com.example.admin.gipp_app.BancoLite.TarefaLiteDAO;
import com.example.admin.gipp_app.Calendario.CalendarActivity;
import com.example.admin.gipp_app.Connections.ConnectionListProjetos;
import com.example.admin.gipp_app.Modelo.Login;
import com.example.admin.gipp_app.Modelo.Projeto;
import com.example.admin.gipp_app.Modelo.Tarefa;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    View progressoa,concluidoa,pendentea,expiradoa,canceladoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConnectionListProjetos prolist = new ConnectionListProjetos();
        Login lg = Login.getInstance();
        prolist.execute(lg.getId());

        final Projeto projeto = new Projeto();

        progressoa = findViewById(R.id.section1);
        progressoa.setVisibility(View.GONE);
        concluidoa = findViewById(R.id.section2);
        concluidoa.setVisibility(View.GONE);
        pendentea  = findViewById(R.id.section3);
        pendentea.setVisibility(View.GONE);
        expiradoa  = findViewById(R.id.section4);
        expiradoa.setVisibility(View.GONE);
        canceladoa = findViewById(R.id.section5);
        canceladoa.setVisibility(View.GONE);

        View em_progresso = findViewById(R.id.em_progresso);
            em_progresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressoa.getVisibility() == View.GONE) {
                    progressoa.setVisibility(View.VISIBLE);
                }else {
                    progressoa.setVisibility(View.GONE);
                }
            }
        });


         View em_concluido = findViewById(R.id.concluido);
            em_concluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (concluidoa.getVisibility() == View.GONE){
                    concluidoa.setVisibility(View.VISIBLE);
                }else{
                    concluidoa.setVisibility(View.GONE);
                }
            }
        });


         View em_pendente = findViewById(R.id.Pendente);
            em_pendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pendentea.getVisibility()== View.GONE){
                    pendentea.setVisibility(View.VISIBLE);
                }else {
                    pendentea.setVisibility(View.GONE);
                }
            }
        });

        View em_expirado = findViewById(R.id.expirado);
            em_expirado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expiradoa.getVisibility()== View.GONE){
                    expiradoa.setVisibility(View.VISIBLE);
                }else {
                    expiradoa.setVisibility(View.GONE);
                }
            }
        });

        View em_cancelado = findViewById(R.id.cancelado);
            em_cancelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canceladoa.getVisibility()== View.GONE){
                    canceladoa.setVisibility(View.VISIBLE);
                }else {
                    canceladoa.setVisibility(View.GONE);
                }
            }
        });






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

        ProjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Projeto projeto = (Projeto) ProjectList.getItemAtPosition(position);

                try {
                    Intent vaiPraListaDeTarefa = new Intent(MainActivity.this, ListaTarefaActivity.class);
                    vaiPraListaDeTarefa.putExtra("projeto", projeto);
                    startActivity(vaiPraListaDeTarefa);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void carregaListaP(){

        ProjetoLiteDAO dao = new ProjetoLiteDAO(this);

        ArrayList<Projeto> Projetos = (ArrayList<Projeto>) dao.buscaProjetosPendentes();


        final ListView ProjectList = (ListView) findViewById(R.id.listaDeProjetosPendentes);
        ArrayAdapter<Projeto> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Projetos);
        ProjectList.setAdapter(arrayAdapter);
        try {
            ProjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Projeto projeto = (Projeto) ProjectList.getItemAtPosition(position);

                    try {
                        Intent vaiPraListaDeTarefa = new Intent(MainActivity.this, ListaTarefaActivity.class);
                        vaiPraListaDeTarefa.putExtra("projeto", projeto);
                        startActivity(vaiPraListaDeTarefa);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaBanco();
        carregaLista();
        carregaListaP();
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
