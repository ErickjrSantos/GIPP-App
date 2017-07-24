package com.example.admin.gipp_app;

import android.content.SyncAdapterType;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Admin on 15/07/2017.
 */


public class ConnectionBank {
    public StringBuffer realizaConexao(String json, String endereco)  {
        try {
            URL url = new URL("http://187.35.128.157:70/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            StringBuffer buffer = new StringBuffer();
            while ((linha = reader.readLine()) != null) {
                buffer.append(linha + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
            return buffer;
        } catch (MalformedURLException e) {
            Log.e("Erro", "Erro na URL", e);
            return null;
        } catch (final IOException e) {
            Log.e("Erro", "Erro fechando o stream", e);
            return null;
        }

    }
}