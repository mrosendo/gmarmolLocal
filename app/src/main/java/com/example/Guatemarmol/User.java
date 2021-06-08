package com.example.Guatemarmol;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    Connection cnxn;
    String nit, name;
    ArrayList<Order> orderArrayList;

    public User(String nit) {
        this.nit = nit;

        connectDB();
    }

    //BUSCAR USUARIO EXISTENTE
    public boolean searchUser() {
        boolean exist = false;

        String query = "SELECT CardName FROM OCRD WHERE U_NIT = " + "'" + this.nit + "'" +  "AND CardType = 'C'";

        try {
            Statement st = cnxn.createStatement();
            ResultSet rs =st.executeQuery(query);
            if (rs.next()) {
                this.name = rs.getString("CardName");
                exist = true;
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return exist;
    }

    //BUSCAR ORDENES
    public ArrayList<Order> getOrders() {
        String query = "SELECT T1.[DocNum], SUBSTRING(CAST(T1.[DocDate] AS VARCHAR), 1, 12) AS FechaString FROM OCRD T0 INNER JOIN OINV T1 ON T0.[CardCode] = T1.[CardCode] WHERE T0.[U_NIT] = " + "'" + this.nit + "'" + " ORDER BY T1.[DocDate] DESC";
        orderArrayList = new ArrayList<>();
        try {
            Statement st = cnxn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Order o = new Order();
                o.setDocDate(rs.getString("FechaString"));
                o.setDocNum(rs.getInt("DocNum"));
                orderArrayList.add(o);
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return orderArrayList;
    }

    //TAMAÑO DE LA LISTA
    public Integer getArraySize() {
        return this.orderArrayList.size();
    }

    //RETORNAR USUARIO
    public String getUserName() {
        return this.name;
    }

    //RETORNAR NIT
    public String getNIT() {
        return this.nit;
    }

    //CONEXION A BASE DE DATOS
    public void connectDB() {
        try {
            DBConnection db = new DBConnection();
            cnxn = db.sqlConnection();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }
}
