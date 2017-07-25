package com.example.admin.gipp_app.Connections;

import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by admin on 25/07/2017.
 */

public class Connection extends AsyncTask {
    private static final String URLlogin = "http://187.35.128.157:70/gerenciatarefas/src/login.php?login=";

    @Override
    protected Object doInBackground(Object[] objects) {
        String u = "kyo";
        String s = "5773";

        try {
            URL url = new URL(URLlogin + u + "&senha=" + s);
            HttpURLConnection conn =(HttpURLConnection)url.openConnection();
            conn.connect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }

}

