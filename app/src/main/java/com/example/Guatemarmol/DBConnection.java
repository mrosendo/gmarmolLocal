package com.example.Guatemarmol;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    String user, pass, ip, port, database;

    public Connection sqlConnection() {
        /*user = "appgt";
        pass = "gtMARMOL.21";
        ip = "104.167.15.244";
        port = "14133";
        database = "App_PDG";*/

        user = "marvin";
        pass = "heavy";
        ip = "192.168.1.53";
        port = "1443";
        database = "PDG";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection con = null;
        String connURL;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databaseName=" + database + ";user=" + user + ";password=" + pass;
            con = DriverManager.getConnection(connURL);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return con;
    }
}
