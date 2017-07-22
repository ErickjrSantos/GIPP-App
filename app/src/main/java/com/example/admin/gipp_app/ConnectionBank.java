package com.example.admin.gipp_app;

import android.util.Log;
import android.widget.Toast;

import com.example.admin.gipp_app.Modelo.Tarefa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Created by Admin on 15/07/2017.
 */


public class ConnectionBank {
        private static final String URL_WEBSERVICE = "http://187.35.128.157:70/GerenciaTarefa/Tarefas";

        public List<Tarefa> tarefa = new Tarefa() {

            HttpURLConnection connection = null;

            try {
                URL url = new URL(URL_WEBSERVICE);

                connection = (HttpURLConnection)url.openConnection();

                InputStream content = connection.getInputStream();

                return ;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                connection.disconnect();
            }
        }
    }

}
